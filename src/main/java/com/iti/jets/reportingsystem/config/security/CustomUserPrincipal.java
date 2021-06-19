//package com.iti.jets.reportingsystem.config.security;
//
//import com.iti.jets.reportingsystem.entities.Userdata;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class CustomUserPrincipal extends User {
//
//    private final Userdata user;
//
//    public CustomUserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//    }
//
//
//    public Userdata getUser() {
//        return user;
//    }
//
//    public String getJobLocation(){
//        return user.getJobLocation();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        final List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserroles().getRole().toUpperCase()));
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
