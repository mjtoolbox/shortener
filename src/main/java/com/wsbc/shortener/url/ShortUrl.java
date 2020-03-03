package com.wsbc.shortener.url;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * DTO between front-end and RestController
 */
public class ShortUrl implements Serializable {

    @NotEmpty(message ="Shortened URL should not be empty or null")
    @URL(message = "Invalid short url")
    private String shortUrl;

//    @NotEmpty(message ="Shortened URL click cannot be empty or null")
//    private int click;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

//    public int getClick() {
//        return click;
//    }
//
//    public void setClick(int click) {
//        this.click = click;
//    }
}
