package com.wsbc.shortener.url;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping(value = "/api/v1")
public class UrlShortenController {

    @Resource
    UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ShortUrl createShortUrl(@Valid @RequestBody LongUrl longUrl){
        return urlShortenerService.createShortUrl(longUrl);
    }


}
