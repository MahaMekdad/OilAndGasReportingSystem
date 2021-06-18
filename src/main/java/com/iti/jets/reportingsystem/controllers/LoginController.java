package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.LoginApi;
import com.iti.jets.openapi.model.LoginRequest;
import com.iti.jets.openapi.model.UserResponse;
import com.iti.jets.reportingsystem.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController implements LoginApi {

    private final UserDataService userDataService;

    @Autowired
    public LoginController(UserDataService userDataService){
        this.userDataService = userDataService;
    }

    @Override
    public ResponseEntity<UserResponse> loginPost(@Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(userDataService.login(loginRequest));
    }
}
