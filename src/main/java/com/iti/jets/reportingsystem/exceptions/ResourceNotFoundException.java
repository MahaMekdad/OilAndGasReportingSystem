package com.iti.jets.reportingsystem.exceptions;


import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException{

    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    protected HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    protected String getDescription() {
        return "No Resource Found With the Given ID";
    }

}
