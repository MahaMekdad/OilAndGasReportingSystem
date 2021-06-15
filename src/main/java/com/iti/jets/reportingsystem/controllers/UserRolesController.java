package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.UsersRolesApi;
import com.iti.jets.openapi.model.UserRoleRequest;
import com.iti.jets.openapi.model.UserRoleResponse;
import com.iti.jets.reportingsystem.services.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserRolesController implements UsersRolesApi {

    private final UserRolesService userRolesService;

    @Autowired
    public UserRolesController(UserRolesService userRolesService){
        this.userRolesService = userRolesService;
    }

    @Override
    public ResponseEntity<List<UserRoleResponse>> usersRolesGet() {
        return ResponseEntity.ok(userRolesService.getAllUserRoles());
    }

    @Override
    public ResponseEntity<Void> usersRolesIdDelete(Integer id) {
        userRolesService.deleteUserRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserRoleResponse> usersRolesIdGet(Integer id) {
        return ResponseEntity.ok(userRolesService.getSpecificUserRole(id));
    }

    @Override
    public ResponseEntity<Void> usersRolesIdPut(Integer id, @Valid UserRoleRequest userRoleRequest) {
        userRolesService.updateUserRole(id, userRoleRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> usersRolesPost(@Valid UserRoleRequest userRoleRequest) {
        userRolesService.insertNewUserRole(userRoleRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
