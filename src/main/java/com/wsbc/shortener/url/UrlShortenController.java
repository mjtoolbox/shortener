package com.wsbc.shortener.url;

import com.wsbc.shortener.util.UrlNotFoundException;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin()
@RestController
public class UrlShortenController {

    @Resource
    UrlShortenerService urlShortenerService;
    @Resource
    UrlShortenRepository urlShortenRepository;

    @PostMapping("/shorten")
    public UrlTransferObject createShortUrl(@Valid @RequestBody UrlShorten urlShorten) {
        return urlShortenerService.createShortUrl(urlShorten);
    }

    @GetMapping("/checkDuplicate/{shortUrl}")
    public UrlTransferObject checkDuplicate(@PathVariable("shortUrl") String shortUrl) {
        return urlShortenerService.checkDuplicate(shortUrl);
    }


    /**
     * DNS Server should map "http://go" to "http://localhost:8080/"
     * where localhost:8080 should be replaced with Azure Web App IP address and its port.
     *
     * @param shortUrl
     * @return
     */
    @GetMapping("/{shortUrl}")
    public ModelAndView redirectToOriginalUrl(@PathVariable("shortUrl") String shortUrl) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            UrlShorten urlShorten = urlShortenerService.redirectShortUrl(shortUrl);
            modelAndView.setViewName("redirect:" + urlShorten.getOriginalUrl());
        } catch (UrlNotFoundException ec) {
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    /**
     * Admin method, skip Service layer.
     * @param urlId
     * @return
     * @throws UrlNotFoundException
     */
    @ApiIgnore
    @GetMapping("/urls/{urlId}")
    public UrlShorten getUrlShorten(@PathVariable Long urlId) throws UrlNotFoundException {
        return  urlShortenRepository.findById(urlId)
                .orElseThrow(() -> new ResourceNotFoundException("URL not found by ID: " + urlId));
    }

    /**
     * Admin method, skip Service layer.
     * @return
     */
    @GetMapping("/urls")
    public List<UrlShorten> retrieveAllUrlShorten(){
        return StreamSupport.stream(urlShortenRepository.findAll(Sort.by("urlId")).spliterator(), false)
                .collect(Collectors.toList());
    }


    /**
     * Admin method, skip Service layer.
     * Cannot set shortUrl as it is treated as PK.
     * @return
     */
    @PutMapping("/urls/{urlId}")
    public UrlShorten updateUrlShorten(@PathVariable long urlId, @Valid @RequestBody UrlShorten urlShorten){
        UrlShorten foundUrl = urlShortenRepository.findById(urlId)
                .orElseThrow(() -> new ResourceNotFoundException("URL not found by ID: " + urlId));
        foundUrl.setOriginalUrl(urlShorten.getOriginalUrl());
        foundUrl.setCreatedBy(urlShorten.getCreatedBy());
        foundUrl.setClick(0);
        return urlShortenRepository.save(foundUrl);
    }


    /**
     * Admin method, skip Service layer.
     * @return
     */
    @DeleteMapping("/urls/{urlId}")
    public void delete(@PathVariable long urlId){
        UrlShorten foundUrl = urlShortenRepository.findById(urlId)
                .orElseThrow(() -> new ResourceNotFoundException("URL not found by ID: " + urlId));
        urlShortenRepository.deleteById(urlId);
    }
}
