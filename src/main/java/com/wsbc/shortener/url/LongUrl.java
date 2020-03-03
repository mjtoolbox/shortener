package com.wsbc.shortener.url;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * DTO between front-end and RestController
 */

public class LongUrl implements Serializable {

    @NotEmpty(message ="Original URL should not be empty or null")
    @URL(message = "Invalid original url")
    private String originalUrl;

    @NotEmpty(message ="Original URL creator cannot be empty")
    private String createdBy;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
