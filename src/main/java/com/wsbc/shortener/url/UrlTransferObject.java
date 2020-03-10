package com.wsbc.shortener.url;

import java.io.Serializable;

/**
 * This TO class is to help easy manipulation of UI. UrlShorten entity has more attributes.
 */
public class UrlTransferObject implements Serializable {

    private String shortUrl;
    private String originalUrl;
    private boolean duplicate;

    public UrlTransferObject() {
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public boolean getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }
}
