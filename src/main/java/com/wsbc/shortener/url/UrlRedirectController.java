package com.wsbc.shortener.url;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@CrossOrigin()
@RestController
public class UrlRedirectController {

    @Resource
    UrlShortenerService urlShortenerService;

//    @GetMapping("/{shorturl}")
//    public void redirectToOriginalUrl(@PathVariable ShortUrl shortUrl){
//        return urlShortenerService.createShortUrl(longUrl);
//    }


}
