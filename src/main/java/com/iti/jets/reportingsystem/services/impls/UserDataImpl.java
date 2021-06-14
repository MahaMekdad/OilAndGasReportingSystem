package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.UserRequest;
import com.iti.jets.openapi.model.UserResponse;
import com.iti.jets.openapi.model.UserRoleResponse;
import com.iti.jets.reportingsystem.entities.Userdata;
import com.iti.jets.reportingsystem.entities.Userroles;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.repos.UserDataRepository;
import com.iti.jets.reportingsystem.repos.UserRolesRepository;
import com.iti.jets.reportingsystem.services.UserDataService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class UserDataImpl implements UserDataService {

    private final UserDataRepository udRepo;
    private final UserRolesRepository urRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public UserDataImpl(UserDataRepository udRepo, UserRolesRepository urRepo, ModelMapper modelMapper){
        this.udRepo = udRepo;
        this.urRepo = urRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponse getSpecificUser(int userId) {
        if(udRepo.findById(userId).isPresent()){
            return modelMapper.map(udRepo.findById(userId).get(), UserResponse.class);
        } else {
            throw new ResourceNotFoundException("No User found with the given ID");
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        Type listType = new TypeToken<List<UserResponse>>(){}.getType();
        List<UserResponse> resultList;
        resultList = modelMapper.map(udRepo.findAll() , listType);
        return resultList;
    }

    @Override
    public List<UserResponse> getAllUsers(Integer jobTitle, String jobLocation) {
        if(jobTitle != null  && !urRepo.findById(jobTitle).isPresent()){
            throw new ResourceNotFoundException("Not a valid UserRole ID");
        }
        if(jobTitle != null && jobLocation != null){
            Type listType = new TypeToken<List<UserResponse>>(){}.getType();
            List<UserResponse> resultList;
            resultList = modelMapper.map(udRepo.findByUserroles_IdEqualsAndJobLocationEquals(jobTitle, jobLocation) , listType);
            return resultList;
        } else if(jobTitle != null){
            Type listType = new TypeToken<List<UserResponse>>(){}.getType();
            List<UserResponse> resultList;
            resultList = modelMapper.map(udRepo.findByUserroles_IdEquals(jobTitle) , listType);
            return resultList;
        } else {
            Type listType = new TypeToken<List<UserResponse>>(){}.getType();
            List<UserResponse> resultList;
            resultList = modelMapper.map(udRepo.findByJobLocationEquals(jobLocation) , listType);
            return resultList;
        }
    }

    @Override
    public void insertNewUser(UserRequest userRequest) {
        Userdata user = new Userdata();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        //todo check if the job title is valid :)
        user.setUserroles(urRepo.findById(userRequest.getJobTitle()).get());
        user.setJobLocation(userRequest.getJobLocation().getValue());
        udRepo.saveAndFlush(user);
    }

    @Override
    public void updateUser(int userId, UserRequest userRequest) {
        if(udRepo.findById(userId).isPresent()){
            Userdata user = udRepo.findById(userId).get();
            if(userRequest.getFirstName() != null) user.setFirstName(userRequest.getFirstName());
            if(userRequest.getLastName() != null) user.setLastName(userRequest.getLastName());
            if(userRequest.getEmail() != null) user.setEmail(userRequest.getEmail());
            if(userRequest.getPhoneNumber() != null) user.setPhoneNumber(userRequest.getPhoneNumber());
            if(userRequest.getJobTitle() != null) user.setUserroles(urRepo.findById(userRequest.getJobTitle()).get());
            if(userRequest.getJobLocation() != null) user.setJobLocation(userRequest.getJobLocation().getValue());
            udRepo.saveAndFlush(user);
        } else {
            throw new ResourceNotFoundException("No User found with the given ID");
        }
    }

    @Override
    public void deleteUser(int userId) {
        if(udRepo.findById(userId).isPresent()){
            udRepo.deleteById(userId);
        } else {
            throw new ResourceNotFoundException("No User found with the given ID");
        }
    }
}
