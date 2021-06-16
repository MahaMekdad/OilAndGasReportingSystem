package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.JobLocation;
import com.iti.jets.openapi.model.LoginRequest;
import com.iti.jets.openapi.model.UserRequest;
import com.iti.jets.openapi.model.UserResponse;
import com.iti.jets.reportingsystem.entities.Userdata;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.exceptions.UserAlreadyExistsException;
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
            Userdata user = udRepo.findById(userId).get();
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            userResponse.setJobTitle(user.getUserroles().getId());
            userResponse.setJobLocation(JobLocation.valueOf(user.getJobLocation().toUpperCase()));
            return userResponse;
        } else {
            throw new ResourceNotFoundException("No User found with the given ID");
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        Type listType = new TypeToken<List<UserResponse>>(){}.getType();
        List<UserResponse> resultList;
        List<Userdata> returnedUserData = udRepo.findAll();
        resultList = modelMapper.map(udRepo.findAll() , listType);
        return setJobRoleAndLocation(listType, returnedUserData);
//        return resultList;
    }

    @Override
    public List<UserResponse> getAllUsers(Integer jobTitle, String jobLocation) {
        if(jobTitle != null  && !urRepo.findById(jobTitle).isPresent()){
            throw new ResourceNotFoundException("Not a valid UserRole ID");
        }
        if(jobTitle != null && jobLocation != null){
            Type listType = new TypeToken<List<UserResponse>>(){}.getType();
            List<UserResponse> resultList;
            List<Userdata> returnedUserData = udRepo.findByUserroles_IdEqualsAndJobLocationEquals(jobTitle, jobLocation);
            return setJobRoleAndLocation(listType, returnedUserData);
        } else if(jobTitle != null){
            Type listType = new TypeToken<List<UserResponse>>(){}.getType();
            List<UserResponse> resultList;
            List<Userdata> returnedUserData = udRepo.findByUserroles_IdEquals(jobTitle);
            return setJobRoleAndLocation(listType, returnedUserData);
        } else {
            Type listType = new TypeToken<List<UserResponse>>(){}.getType();
            List<UserResponse> resultList;
            List<Userdata> returnedUserData = udRepo.findByJobLocationEquals(jobLocation);
            return setJobRoleAndLocation(listType, returnedUserData);
        }
    }

    private List<UserResponse> setJobRoleAndLocation(Type listType, List<Userdata> returnedUserData) {
        List<UserResponse> resultList;
        resultList = modelMapper.map(returnedUserData, listType);
        for(int i = 0; i < returnedUserData.size(); i++){
            resultList.get(i).setJobTitle(returnedUserData.get(i).getUserroles().getId());
            resultList.get(i).setJobLocation(JobLocation.valueOf(returnedUserData.get(i).getJobLocation().toUpperCase()));
        }
        return resultList;
    }

    @Override
    public void insertNewUser(UserRequest userRequest) {
        Userdata exists = udRepo.findByEmailEquals(userRequest.getEmail());
        if(exists != null) throw new UserAlreadyExistsException("There is an account registered with this email");
        Userdata user = new Userdata();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setPassword(userRequest.getPassword());
        //todo check if the job title is valid :)
        if(!urRepo.findById(userRequest.getJobTitle()).isPresent()){
            throw new ResourceNotFoundException("No User Role found with the given ID, please check your entered data");
        }
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
            if(userRequest.getPassword() != null) user.setPassword(userRequest.getPassword());
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

    public void addUserRole(int roleId, int userId){
        Userdata userToUpdate = udRepo.findById(userId).get();
        userToUpdate.setUserroles(urRepo.findById(roleId).get());
    }

    public UserResponse login(LoginRequest loginRequest){
        Userdata userData = udRepo.findByEmailEqualsAndPasswordEquals(loginRequest.getEmail(), loginRequest.getPassword());
        if(userData == null){
            throw new ResourceNotFoundException("No User found with the given credentials");
        } else {
            UserResponse user = modelMapper.map(udRepo.findByEmailEqualsAndPasswordEquals(loginRequest.getEmail(), loginRequest.getPassword()), UserResponse.class);
            user.setJobLocation(JobLocation.valueOf(userData.getJobLocation().toUpperCase()));
            user.setJobTitle(userData.getUserroles().getId());
            return user;
        }
    }
}
