package com.wsbc.shortener.url;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UrlShortenRepository extends PagingAndSortingRepository<UrlShorten, Long> {
    UrlShorten findByShortUrl(String shortUrl);
}
