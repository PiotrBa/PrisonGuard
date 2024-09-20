package com.piotrba.guards.exeptionHandler;

public class GuardNotFoundException extends RuntimeException {

    public GuardNotFoundException(String message) {
        super(message);
    }
}
