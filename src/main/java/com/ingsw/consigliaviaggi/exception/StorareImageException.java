package com.ingsw.consigliaviaggi.exception;

public class StorareImageException extends RuntimeException {

    private final String message ;


    public StorareImageException(String message) {
        super(message);
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
