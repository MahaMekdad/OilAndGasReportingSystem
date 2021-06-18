package com.iti.jets.reportingsystem.exceptions;

import org.springframework.http.HttpStatus;

public class IntegrityConstraintException extends ApiException{

    public IntegrityConstraintException(String msg) {
        super(msg);
    }

    @Override
    protected HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    protected String getDescription() {
        return "Cannot delete this resource as other entities depend on it (foreign key relations)";
    }
}
