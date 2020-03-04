package com.wsbc.shortener.util;

public class UrlAlreadyExistException extends RuntimeException{

    private static final long serialVersionUID = 2992901119502842099L;

    public UrlAlreadyExistException() {
        this("URL already exists!");
    }

    public UrlAlreadyExistException(String message) {
        this(message, (Throwable)null);
    }

    public UrlAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
