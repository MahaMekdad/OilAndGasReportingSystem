package com.iti.jets.reportingsystem.utils.helpers;

import com.iti.jets.reportingsystem.repos.UserDataRepository;
import com.iti.jets.reportingsystem.repos.WellRepo;
import com.iti.jets.reportingsystem.utils.jwt.JwtUtil;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("mySecurityService")
public class RepoHelper {

    private final WellRepo wellRepo;
    private static WellRepo wellRepo2;
    private static UserDataRepository udRepo;
    private final JwtUtil jwtUtil;
    public static String token;

    @Autowired
    public RepoHelper(WellRepo wellRepo, JwtUtil jwtUtil, UserDataRepository udRepo){
        this.wellRepo = wellRepo;
        this.jwtUtil = jwtUtil;
        RepoHelper.wellRepo2 = wellRepo;
        RepoHelper.udRepo = udRepo;
    }

    public static String getConcessionName(Integer wellId){
        return wellRepo2.findById(wellId).get().getField().getConcession().getConcessionName();
    }

    public static String getUserLocationName(String email){
        return udRepo.findByEmailEquals(email).getJobLocation();
    }

//    public boolean isFlmConcessionMember(Integer wellId){
//        if(token != null){
//            Map claims = jwtUtil.extractAllClaims(token);
//            String jobLocation = claims.get("location").toString();
//            String concessionName =  wellRepo.findById(wellId).get().getField().getConcession().getConcessionName();
//            if(jobLocation.equals(concessionName)){
//                token = null;
//                return true;
//            } else {
//                token = null;
//                return false;
//            }
//        } else {
//            System.out.println("no token, meaning no request with a valid token");
//            return false;
//        }
//    }
}
