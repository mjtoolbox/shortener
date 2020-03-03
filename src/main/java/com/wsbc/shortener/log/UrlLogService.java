package com.wsbc.shortener.log;

import com.wsbc.shortener.url.UrlShortenRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UrlLogService {

    @Resource
    UrlShortenRepository urlShortenRepository;
}
