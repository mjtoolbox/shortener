package com.wsbc.shortener.url;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UrlShortenRepository extends PagingAndSortingRepository<UrlShorten, Long> {
    UrlShorten findByShortUrl(String shortUrl);
}
