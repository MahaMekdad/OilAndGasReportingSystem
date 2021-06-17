package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.LoginApi;
import com.iti.jets.openapi.model.LoginRequest;
import com.iti.jets.openapi.model.UserLoggedInResponse;
import com.iti.jets.openapi.model.UserResponse;
import com.iti.jets.reportingsystem.exceptions.InvalidCredentials;
import com.iti.jets.reportingsystem.services.UserDataService;
import com.iti.jets.reportingsystem.utils.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController implements LoginApi {

    private final UserDataService userDataService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginController(UserDataService userDataService, AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this.userDataService = userDataService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<UserLoggedInResponse> loginPost(@Valid LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new InvalidCredentials("invalid username/password");
        }
        String userAccessToken = jwtUtil.generateToken(loginRequest.getEmail());
        UserLoggedInResponse user = userDataService.login(loginRequest);
        user.setAccessToken(userAccessToken);
        return ResponseEntity.ok(user);
    }
}
