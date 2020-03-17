package com.example.demo.advice;

import com.example.demo.exception.GenericError;
import com.example.demo.exception.PostRetrievalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class PostRetrievalExceptionAdvice {

    @ExceptionHandler(value = {PostRetrievalException.class})
    public ResponseEntity<GenericError> mapException(PostRetrievalException pre) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        GenericError genericError = new GenericError(pre.getMessage(), pre, httpStatus, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<GenericError>(genericError, httpStatus);
    }
}
