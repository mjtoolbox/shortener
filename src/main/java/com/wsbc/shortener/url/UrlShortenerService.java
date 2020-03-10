package com.wsbc.shortener.url;

import com.wsbc.shortener.log.UrlLogService;
import com.wsbc.shortener.util.UrlNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UrlShortenerService {

    @Resource
    UrlShortenRepository urlShortenRepository;

    @Resource
    UrlLogService urlLogService;

    /**
     * If exists, just return UrlTO. Otherwise, save.
     *
     * @param urlShorten
     * @return
     */
    public UrlTransferObject createShortUrl(UrlShorten urlShorten) {
        // Find shortrul exists, return UrlTO with a message
        UrlShorten foundUrlShorten = urlShortenRepository.findByShortUrl(urlShorten.getShortUrl());
        UrlTransferObject urlTransferObject = new UrlTransferObject();
        if (foundUrlShorten != null) {
            urlTransferObject.setShortUrl(foundUrlShorten.getShortUrl());
            urlTransferObject.setOriginalUrl(foundUrlShorten.getOriginalUrl());
            urlTransferObject.setDuplicate(true);
        } else {
            UrlShorten persistedUrlShorten = urlShortenRepository.save(urlShorten);
            urlTransferObject.setShortUrl(persistedUrlShorten.getShortUrl());
            urlTransferObject.setOriginalUrl(persistedUrlShorten.getOriginalUrl());
            urlTransferObject.setDuplicate(false);
        }
        return urlTransferObject;
    }



    /**
     * Return UrlTransferObject
     *
     * @param shortUrl
     * @return
     */
    public UrlTransferObject checkDuplicate(String shortUrl) {
        UrlShorten foundUrlShorten = urlShortenRepository.findByShortUrl(shortUrl);
        UrlTransferObject urlTransferObject = new UrlTransferObject();
        if (foundUrlShorten != null) {
            urlTransferObject.setShortUrl(foundUrlShorten.getShortUrl());
            urlTransferObject.setOriginalUrl(foundUrlShorten.getOriginalUrl());
            urlTransferObject.setDuplicate(true);
        } else {
            urlTransferObject.setDuplicate(false);
        }
        return urlTransferObject;
    }

    /**
     * Increment the count and log, then redirect
     *
     * @param shortUrl
     * @return
     */
    public UrlShorten redirectShortUrl(String shortUrl) {
        UrlShorten foundUrlShorten = urlShortenRepository.findByShortUrl(shortUrl);
        if (foundUrlShorten != null) {
            foundUrlShorten.increaseClick();
            urlLogService.persistLog(shortUrl);
            return urlShortenRepository.save(foundUrlShorten);
        } else {
            throw new UrlNotFoundException("ShortURL not found!" + shortUrl);
        }
    }

}
