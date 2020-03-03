package com.wsbc.shortener.log;

import com.wsbc.shortener.url.UrlShorten;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UrlLogRepository extends PagingAndSortingRepository<UrlShorten, Long> {

}
