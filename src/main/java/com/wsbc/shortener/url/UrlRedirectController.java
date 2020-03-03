package com.wsbc.shortener.url;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import java.util.Optional;

@CrossOrigin()
@RestController
public class UrlRedirectController {

    @Resource
    UrlShortenerService urlShortenerService;

    @GetMapping("/short/{shortUrl}")
    public ModelAndView redirectToOriginalUrl(@PathVariable("shortUrl") String shortUrl){
        return urlShortenerService.findShortUrl(shortUrl)
                .map(urlShorten -> new ModelAndView("redirect:"+ urlShorten.getOriginalUrl()))
                .orElseGet(()-> new ModelAndView("not_found.html"));
    }

    @GetMapping("/shortener/{shortUrl}")
    public Optional<UrlShorten> getUrlShorten(@PathVariable String shortUrl){
        return urlShortenerService.findShortUrl(shortUrl);
//                .orElseThrow(() -> new ResourceNotFoundException("UrlShorten not found with shortUrl: " + shortUrl));
    }

}
