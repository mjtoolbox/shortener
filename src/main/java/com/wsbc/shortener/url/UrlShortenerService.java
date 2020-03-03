package com.wsbc.shortener.url;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UrlShortenerService {

    @Resource
    UrlShortenRepository urlShortenRepository;

    public UrlShorten createShortUrl(UrlShorten urlShorten){
        // Find shortrul exists, if so throw exception, otherwise save
        if (urlShortenRepository.findByShortUrl(urlShorten.getShortUrl()).isPresent()){
            throw new DuplicateKeyException("ShortURL already exists: " + urlShorten.getShortUrl());
        }
        return urlShortenRepository.save(urlShorten);
    }

    public Optional<UrlShorten> findShortUrl(String shortUrl){
        return urlShortenRepository.findByShortUrl(shortUrl);
    }
}
