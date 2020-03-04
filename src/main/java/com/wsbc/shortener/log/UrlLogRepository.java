package com.wsbc.shortener.log;

import com.wsbc.shortener.url.UrlShorten;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UrlLogRepository extends PagingAndSortingRepository<UrlShorten, Long> {
    List<UrlLog> findByUrlId(String shortUrl);
}
