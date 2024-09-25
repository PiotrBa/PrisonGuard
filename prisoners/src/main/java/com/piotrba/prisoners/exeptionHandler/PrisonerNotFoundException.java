package com.piotrba.prisoners.exeptionHandler;

public class PrisonerNotFoundException extends RuntimeException{

    public PrisonerNotFoundException(String message) {
        super(message);
    }
}
