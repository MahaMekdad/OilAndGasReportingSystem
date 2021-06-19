//package com.iti.jets.reportingsystem.config.security;
//
//import com.iti.jets.reportingsystem.utils.helpers.RepoHelper;
//import org.springframework.security.access.expression.SecurityExpressionRoot;
//import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//
//public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
//
//    private Object filterObject;
//    private Object returnObject;
//
//    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
//        super(authentication);
//    }
//
//    public boolean isFlmConcessionMember(Integer wellId) {
//        final User user = (User) this.getPrincipal();
//        return RepoHelper.getUserLocationName(user.getUsername()).equals(RepoHelper.getConcessionName(wellId));
//    }
//
//    @Override
//    public void setFilterObject(Object o) {
//        this.filterObject = o;
//    }
//
//    @Override
//    public Object getFilterObject() {
//        return this.filterObject;
//    }
//
//    @Override
//    public void setReturnObject(Object o) {
//        this.returnObject = o;
//    }
//
//    @Override
//    public Object getReturnObject() {
//        return this.returnObject;
//    }
//
//    @Override
//    public Object getThis() {
//        return this;
//    }
//}
