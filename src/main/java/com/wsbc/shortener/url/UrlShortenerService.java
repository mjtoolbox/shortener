package com.wsbc.shortener.url;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UrlShortenerService {

    @Resource
    UrlShortenRepository urlShortenRepository;

    public ShortUrl createShortUrl(LongUrl longUrl){
        UrlShorten urlShorten = new UrlShorten(longUrl.getOriginalUrl(), longUrl.getCreatedBy());
        UrlShorten shorten = urlShortenRepository.save(urlShorten);
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setShortUrl(shorten.getShortUrl());
        return shortUrl;
    }

//    public ShortUrl findShortUrl(ShortUrl shortUrl){
//        return urlShortenRepository.findby
//    }
}
