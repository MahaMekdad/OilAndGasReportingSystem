package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.UserRoleRequest;
import com.iti.jets.openapi.model.UserRoleResponse;
import com.iti.jets.reportingsystem.entities.Userroles;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.repos.ProductionGeneralInfoRepository;
import com.iti.jets.reportingsystem.repos.UserRolesRepository;
import com.iti.jets.reportingsystem.repos.WellRepo;
import com.iti.jets.reportingsystem.services.UserRolesService;
import com.iti.jets.reportingsystem.utils.mappers.ProductionGeneralInfoMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class UserRolesImpl implements UserRolesService {

    private final UserRolesRepository urRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRolesImpl(UserRolesRepository urRepo, ModelMapper modelMapper){
        this.urRepo = urRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<UserRoleResponse> getAllUserRoles() {
        Type listType = new TypeToken<List<UserRoleResponse>>(){}.getType();
        List<UserRoleResponse> resultList;
        resultList = modelMapper.map(urRepo.findAll() , listType);
        return resultList;
    }

    @Override
    public UserRoleResponse getSpecificUserRole(int userRoleId) {
        if(urRepo.findById(userRoleId).isPresent()){
            return modelMapper.map(urRepo.findById(userRoleId).get(), UserRoleResponse.class);
        } else {
            throw new ResourceNotFoundException("No User Role found with the given ID");
        }
    }

    @Override
    public void insertNewUserRole(UserRoleRequest userRole) {
        urRepo.saveAndFlush(modelMapper.map(userRole, Userroles.class));
    }

    @Override
    public void updateUserRole(int userRoleId, UserRoleRequest userRole) {
        if(urRepo.findById(userRoleId).isPresent()){
            Userroles userrole = urRepo.findById(userRoleId).get();
            if(userRole.getRole()!= null) userrole.setRole(userRole.getRole());
            if(userrole.getUserdatas() != null) userrole.setUserdatas(userrole.getUserdatas());
            urRepo.saveAndFlush(userrole);
        } else {
            throw new ResourceNotFoundException("No User Role found with the given ID");
        }
    }

    @Override
    public void deleteUserRole(int userRoleId) {
        if(urRepo.findById(userRoleId).isPresent()){
            urRepo.deleteById(userRoleId);
        } else {
            throw new ResourceNotFoundException("No User Role found with the given ID");
        }
    }
}
