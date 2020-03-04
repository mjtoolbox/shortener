package com.wsbc.shortener.log;

import com.wsbc.shortener.url.UrlShorten;
import com.wsbc.shortener.url.UrlShortenRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UrlLogService {

    @Resource
    UrlLogRepository urlLogRepository;

    @Resource
    UrlShortenRepository urlShortenRepository;

    public List<UrlLog> retrieveLogByShortUrl(String shortUrl){
       UrlShorten urlShorten =  urlShortenRepository.findByShortUrl(shortUrl);
       return urlLogRepository.findByUrlId(urlShorten.getShortUrl());
    }

    public void persistLog(String shortUrl){
        UrlShorten urlShorten =  urlShortenRepository.findByShortUrl(shortUrl);
        UrlLog urlLog = new UrlLog();
        urlLog.setShortUrl(urlShorten.getShortUrl());
        urlLog.setUrlId(urlShorten.getUrlId());
        urlLog.setLast_accessed(new Date());
        urlLog.setUrlShorten(urlShorten);
        System.out.println("*************" + urlLog);
        urlLogRepository.save(urlLog);
    }
}
