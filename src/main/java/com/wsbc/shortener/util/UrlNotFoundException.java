package com.wsbc.shortener.util;

public class UrlNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 7992904489502842099L;

    public UrlNotFoundException() {
        this("URL not found!");
    }

    public UrlNotFoundException(String message) {
        this(message, (Throwable)null);
    }

    public UrlNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
