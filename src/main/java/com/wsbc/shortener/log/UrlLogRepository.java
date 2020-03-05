package com.wsbc.shortener.log;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UrlLogRepository extends PagingAndSortingRepository<UrlLog, Long> {
    List<UrlLog> findByShortUrl(String shortUrl);
}
