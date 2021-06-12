package com.iti.jets.reportingsystem.exceptions;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException{

    protected ApiException(String msg){super(msg);}

    protected abstract HttpStatus getHttpStatus();

    protected abstract String getDescription();
}
