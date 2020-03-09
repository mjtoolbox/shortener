package com.wsbc.shortener.url;

import com.wsbc.shortener.log.UrlLogService;
import com.wsbc.shortener.util.UrlAlreadyExistException;
import com.wsbc.shortener.util.UrlNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UrlShortenerService {

    @Resource
    UrlShortenRepository urlShortenRepository;

    @Resource
    UrlLogService urlLogService;

    public UrlShorten createShortUrl(UrlShorten urlShorten) throws UrlAlreadyExistException{
        // Find shortrul exists, if so throw exception, otherwise save
        if (urlShortenRepository.findByShortUrl(urlShorten.getShortUrl()) != null){
            throw new UrlAlreadyExistException("ShortURL already exists: " + urlShorten.getShortUrl());
        }
        return urlShortenRepository.save(urlShorten);
    }

    /**
     * Return UrlTransferObject
     * @param shortUrl
     * @return
     */
    public UrlTransferObject checkDuplicate(String shortUrl){
        UrlShorten foundUrlShorten = urlShortenRepository.findByShortUrl(shortUrl);
        UrlTransferObject urlTransferObject = new UrlTransferObject();
        if (foundUrlShorten != null){
            urlTransferObject.setShortUrl(foundUrlShorten.getShortUrl());
            urlTransferObject.setOriginalUrl(foundUrlShorten.getOriginalUrl());
            urlTransferObject.setDuplicate(true);
        }else{
            urlTransferObject.setDuplicate(false);
        }
        return urlTransferObject;
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
            urlLogService.persistLog(shortUrl);
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
