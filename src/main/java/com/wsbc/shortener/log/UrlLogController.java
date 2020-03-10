package com.wsbc.shortener.log;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin()
@RestController
public class UrlLogController {

    @Resource
    UrlLogService urlLogService;

    @GetMapping("/logs/{shortUrl}")
    public List<UrlLog> retrieveLogByShortUrl(@PathVariable("shortUrl") String shortUrl){
        return urlLogService.retrieveLogByShortUrl(shortUrl);
    }

}
