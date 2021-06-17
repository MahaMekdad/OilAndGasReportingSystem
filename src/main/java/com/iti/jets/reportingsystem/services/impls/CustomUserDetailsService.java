package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.Userdata;
import com.iti.jets.reportingsystem.exceptions.InvalidCredentials;
import com.iti.jets.reportingsystem.repos.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserDataRepository userDataRepository;

    @Autowired
    public CustomUserDetailsService(UserDataRepository userDataRepository){
        this.userDataRepository = userDataRepository;
    }

    //taking the email from the user
    //get the user with this email from the db
    //give the user to spring security to validate email and password
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Userdata user = userDataRepository.findByEmailEquals(email);
        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
        } else {
            throw new InvalidCredentials("Invalid email");
        }
    }
}
