package com.piotrba.prisoners.exeptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PrisonerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePrisonerNotFoundException(PrisonerNotFoundException e) {
        logger.error("Prisoner not found: {}", e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleIllegalStateException(IllegalStateException e) {
        logger.error("Illegal state: {}", e.getMessage());
        return e.getMessage();
    }
}
