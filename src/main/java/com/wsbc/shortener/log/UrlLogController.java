package com.wsbc.shortener.log;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin()
@RestController
public class UrlLogController {

    @Resource
    UrlLogService urlLogService;

//    @GetMapping("/log/{shortUrl}")
//    public List<UrlLog> retrieveLogByShortUrl(@PathVariable("shortUrl") String shortUrl){
//        return urlLogService.retrieveLogByShortUrl(shortUrl);
//    }
}
