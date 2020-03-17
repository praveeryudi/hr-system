package com.example.demo.exception;

public class PostRetrievalException extends Exception {

    public PostRetrievalException(String message) {
        super(message);
    }

    public PostRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
