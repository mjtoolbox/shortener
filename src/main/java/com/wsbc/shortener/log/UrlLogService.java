package com.wsbc.shortener.log;

import com.wsbc.shortener.url.UrlShortenRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UrlLogService {

    @Resource
    UrlLogRepository urlLogRepository;

    @Resource
    UrlShortenRepository urlShortenRepository;

//    public List<UrlLog> retrieveLogByShortUrl(String shortUrl){
//       UrlShorten urlShorten =  urlShortenRepository.findByShortUrl(shortUrl);
//       return urlLogRepository.findByUrlShorten(urlShorten);
//    }
}
