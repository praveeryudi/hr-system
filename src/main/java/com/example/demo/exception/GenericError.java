package com.example.demo.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class GenericError {

    private final String errorMessage;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public GenericError(String errorMessage,
                        Throwable throwable,
                        HttpStatus httpStatus,
                        ZonedDateTime timestamp) {

        this.errorMessage = errorMessage;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
