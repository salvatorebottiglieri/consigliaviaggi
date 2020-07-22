package com.ingsw.consigliaviaggi.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Richiesta JSON non ben formata";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }


    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @Override

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST);
        return buildResponseEntity(error);
    }

    @ExceptionHandler(value = NoValidInputException.class)
    protected ResponseEntity<Object> handleInputNotValid(NoValidInputException noValidInputException){
        ApiError apiError = new ApiError(NOT_ACCEPTABLE);
        apiError.setMessage(noValidInputException.getMessage());
        return buildResponseEntity(apiError);

    }

    @ExceptionHandler(value = ElementIsAlreadyPresentExcetpion.class)
    protected ResponseEntity<Object> handleInputNotValid(ElementIsAlreadyPresentExcetpion structureIsAlreadyPresentExcetpion){
        ApiError apiError = new ApiError(NOT_ACCEPTABLE);
        apiError.setMessage(structureIsAlreadyPresentExcetpion.getMessage());
        return buildResponseEntity(apiError);
    }



}