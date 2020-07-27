package com.ingsw.consigliaviaggi.exception;



public class ElementIsAlreadyPresentExcetpion extends RuntimeException {

    private final String message ;


    public ElementIsAlreadyPresentExcetpion(String message) {
        super(message);
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
