package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.LoginRequest;
import com.iti.jets.openapi.model.UserLoggedInResponse;
import com.iti.jets.openapi.model.UserRequest;
import com.iti.jets.openapi.model.UserResponse;

import java.util.List;

public interface UserDataService {

    UserResponse getSpecificUser(int userId);

    List<UserResponse> getAllUsers();

    List<UserResponse> getAllUsers(Integer jobTitle, String jobLocation);

    void insertNewUser(UserRequest userRequest);

    void updateUser(int userId, UserRequest userRequest);

    void deleteUser(int userId);

    void addUserRole(int roleId, int userId);

    UserLoggedInResponse login(LoginRequest loginRequest);
}
