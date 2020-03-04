package com.wsbc.shortener.url;

import com.wsbc.shortener.util.UrlAlreadyExistException;
import com.wsbc.shortener.util.UrlNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UrlShortenerService {

    @Resource
    UrlShortenRepository urlShortenRepository;

    public UrlShorten createShortUrl(UrlShorten urlShorten){
        // Find shortrul exists, if so throw exception, otherwise save
        if (urlShortenRepository.findByShortUrl(urlShorten.getShortUrl()) != null){
            throw new UrlAlreadyExistException("ShortURL already exists: " + urlShorten.getShortUrl());
        }
        return urlShortenRepository.save(urlShorten);
    }

    /**
     * Increment the count and log, then redirect
     * @param shortUrl
     * @return
     */
    public UrlShorten findShortUrl(String shortUrl){
        UrlShorten foundUrlShorten = urlShortenRepository.findByShortUrl(shortUrl);
        if (foundUrlShorten != null){
            foundUrlShorten.increaseClick();
            return urlShortenRepository.save(foundUrlShorten);
        }else{
            throw new UrlNotFoundException("ShortURL not found!" + shortUrl);
        }
    }

    public UrlShorten findShortUrlWithoutClickIncrease(String shortUrl){
        UrlShorten foundUrlShorten = urlShortenRepository.findByShortUrl(shortUrl);
        if (foundUrlShorten != null){
            return urlShortenRepository.save(foundUrlShorten);
        }else{
            throw new UrlNotFoundException("ShortURL not found!" + shortUrl);
        }
    }
}
