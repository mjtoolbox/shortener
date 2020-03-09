package com.wsbc.shortener.url;

import com.wsbc.shortener.util.UrlAlreadyExistException;
import com.wsbc.shortener.util.UrlNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;

@CrossOrigin()
@RestController
public class UrlShortenController {

    @Resource
    UrlShortenerService urlShortenerService;
    @Resource
    UrlShortenRepository urlShortenRepository;

    @PostMapping("/shorten")
    public UrlShorten createShortUrl(@Valid @RequestBody UrlShorten urlShorten){
        try {
            return urlShortenerService.createShortUrl(urlShorten);
        }catch (UrlAlreadyExistException ec){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "ShortURL already exists", ec);
        }
    }

    @GetMapping("/checkDuplicate/{shortUrl}")
    public UrlTransferObject checkDuplicate(@PathVariable("shortUrl") String shortUrl){
        return urlShortenerService.checkDuplicate(shortUrl);
    }


    @GetMapping("/redirect/{shortUrl}")
    public ModelAndView redirectToOriginalUrl(@PathVariable("shortUrl") String shortUrl){
        UrlShorten urlShorten = urlShortenerService.findShortUrl(shortUrl);
        return new ModelAndView("redirect:" + urlShorten.getOriginalUrl());
    }

    @GetMapping("/display/{shortUrl}")
    public UrlShorten getUrlShorten(@PathVariable String shortUrl){

        UrlShorten urlShorten = urlShortenerService.findShortUrlWithoutClickIncrease(shortUrl);
        if (urlShorten == null){
            throw new UrlNotFoundException("ShortURL not found: " + shortUrl);
        }
       return urlShorten;
    }
}
