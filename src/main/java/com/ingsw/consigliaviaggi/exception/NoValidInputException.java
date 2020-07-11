package com.ingsw.consigliaviaggi.exception;

public class NoValidInputException extends RuntimeException{

    private final String message;

    public NoValidInputException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
