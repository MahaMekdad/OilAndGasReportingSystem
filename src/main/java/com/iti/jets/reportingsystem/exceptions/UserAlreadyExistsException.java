package com.iti.jets.reportingsystem.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ApiException{


    public UserAlreadyExistsException(String msg) {
        super(msg);
    }

    @Override
    protected HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    protected String getDescription() {
        return "User Already Registered";
    }
}
