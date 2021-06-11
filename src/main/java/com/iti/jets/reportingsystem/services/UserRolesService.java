package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.UserRoleRequest;
import com.iti.jets.openapi.model.UserRoleResponse;
import com.iti.jets.reportingsystem.entities.Userroles;

import java.util.List;

public interface UserRolesService {

    List<UserRoleResponse> getAllUserRoles();

    UserRoleResponse getSpecificUserRole(int userRoleId);

    void insertNewUserRole(UserRoleRequest userRole);

    void updateUserRole(int userRoleId, UserRoleRequest userRole);

    void deleteUserRole(int userRoleId);
}
