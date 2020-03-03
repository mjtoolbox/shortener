package com.wsbc.shortener.log;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin()
@RestController
public class UrlLogController {

    @Resource
    UrlLogService urlLogService;
}
