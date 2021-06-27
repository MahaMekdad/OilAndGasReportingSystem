package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.Userdata;
import com.iti.jets.reportingsystem.exceptions.InvalidCredentials;
import com.iti.jets.reportingsystem.repos.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
        } else {
            throw new InvalidCredentials("Invalid email");
        }
    }

//    @Transactional
    protected Set getAuthority(Userdata user) {
        Set authorities = new HashSet<>();
        System.out.println("ROLE_" + user.getUserroles().getRole().toUpperCase());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserroles().getRole().toUpperCase()));
        return authorities;
    }
}
