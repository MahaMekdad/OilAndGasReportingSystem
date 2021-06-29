package com.iti.jets.reportingsystem.exceptions;

import org.springframework.http.HttpStatus;

public class UserRoleException extends ApiException {

    public UserRoleException(String msg) {
        super(msg);
    }

    @Override
    protected HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    protected String getDescription() {
        return "You Haven't Been Assigned A Role Yet";
    }
}
