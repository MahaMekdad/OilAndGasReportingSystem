package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.UsersApi;
import com.iti.jets.openapi.model.UserRequest;
import com.iti.jets.openapi.model.UserResponse;
import com.iti.jets.reportingsystem.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserDataController implements UsersApi {

    private final UserDataService userDataService;

    @Autowired
    public UserDataController(UserDataService userDataService){
        this.userDataService = userDataService;
    }

    @Override
    public ResponseEntity<List<UserResponse>> usersGet(@Valid Integer jobRole, @Valid String jobLocation) {
        if(jobRole != null || jobLocation != null){
            return ResponseEntity.ok(userDataService.getAllUsers(jobRole, jobLocation));
        } else {
            return ResponseEntity.ok(userDataService.getAllUsers());
        }
    }

    @Override
    public ResponseEntity<Void> usersIdDelete(Integer id) {
        userDataService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> usersIdGet(Integer id) {
        return ResponseEntity.ok(userDataService.getSpecificUser(id));
    }

    @Override
    public ResponseEntity<Void> usersIdPut(Integer id, @Valid UserRequest userRequest) {
        userDataService.updateUser(id, userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> usersPost(@Valid UserRequest userRequest) {
        userDataService.insertNewUser(userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
