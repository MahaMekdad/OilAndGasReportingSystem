package com.iti.jets.reportingsystem.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCredentials extends ApiException{

    public InvalidCredentials(String msg) {
        super(msg);
    }

    @Override
    protected HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    protected String getDescription() {
        return "Wrong email or password";
    }
}
