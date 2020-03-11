package com.wsbc.shortener.log;

import com.wsbc.shortener.url.UrlShorten;
import com.wsbc.shortener.url.UrlShortenRepository;
import com.wsbc.shortener.util.UrlNotFoundException;
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


    /**
     * Retrieve click logs by
     * @param shortUrl
     * @return
     */
    public List<UrlLog> retrieveLogByShortUrl(String shortUrl){
       UrlShorten urlShorten =  urlShortenRepository.findByShortUrl(shortUrl);
       if (urlShorten == null){
           throw new UrlNotFoundException("ShortURL not found!" + shortUrl);
       }
       return urlLogRepository.findByShortUrlDesc(shortUrl);
    }

    public void persistLog(String shortUrl){

        UrlShorten urlShorten =  urlShortenRepository.findByShortUrl(shortUrl);

        UrlLog urlLog = new UrlLog(urlShorten.getUrlId(),shortUrl, new Date());
        urlLogRepository.save(urlLog);
    }
}
