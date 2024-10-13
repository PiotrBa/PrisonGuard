package com.piotrba.visitors.exeptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(VisitorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleVisitorNotFoundException(VisitorNotFoundException e){
        logger.error("Visitor not found: {}", e.getMessage());
        return e.getMessage();
    }
}
