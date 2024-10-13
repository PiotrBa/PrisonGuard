package com.piotrba.visitors.exeptionHandler;

public class VisitorNotFoundException extends RuntimeException{

    public VisitorNotFoundException(String message) {
        super(message);
    }
}
