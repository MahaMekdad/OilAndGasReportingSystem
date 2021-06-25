package com.iti.jets.reportingsystem.utils.helpers;

import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.repos.*;
import com.iti.jets.reportingsystem.utils.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("mySecurityService")
public class RepoHelper {

    private final WellRepo wellRepo;
    private static WellRepo wellRepo2;
    private static UserDataRepository udRepo;
    private final WellGeneralInfoRepository wellGenInfoRepo;
    private final IntervalsInfoRepository intervalsRepo;
    private final FieldRepository fieldRepo;
    private final ProductionBudgetRepository pbRepo;
    private final ConcessionRepository conRepo;
    private final JwtUtil jwtUtil;

    public static String token;
    private ThreadLocal<String> threadLocalValue;

    @Autowired
    public RepoHelper(WellRepo wellRepo, JwtUtil jwtUtil, UserDataRepository udRepo, WellGeneralInfoRepository wellGenInfoRepo, IntervalsInfoRepository intervalsRepo, FieldRepository fieldRepo, ProductionBudgetRepository pbRepo, ConcessionRepository conRepo){
        this.wellRepo = wellRepo;
        this.jwtUtil = jwtUtil;
        RepoHelper.wellRepo2 = wellRepo;
        RepoHelper.udRepo = udRepo;
        this.wellGenInfoRepo = wellGenInfoRepo;
        this.intervalsRepo = intervalsRepo;
        this.fieldRepo = fieldRepo;
        this.pbRepo = pbRepo;
        this.conRepo = conRepo;
    }

    public ThreadLocal<String> getThreadLocalValue() {
        return threadLocalValue;
    }

    public void setThreadLocalValue(ThreadLocal<String> threadLocalValue) {
        this.threadLocalValue = threadLocalValue;
    }

    public static String getConcessionName(Integer wellId){
        if(wellRepo2.findById(wellId).isPresent()){
            return wellRepo2.findById(wellId).get().getField().getConcession().getConcessionName();
        } else {
            throw new ResourceNotFoundException("No well found with the given id");
        }
    }

    public static String getUserLocationName(String email){
        return udRepo.findByEmailEquals(email).getJobLocation();
    }

    public boolean isFlmConcessionMember(Integer wellId){
        String token = ThreadLocalToken.getCurrentInstance().getToken();
        System.out.println(token + "<===============#");
        if(token != null){
            Map claims = jwtUtil.extractAllClaims(token);
            String jobLocation = claims.get("location").toString();
            if(wellRepo.findById(wellId).isPresent()){
                String concessionName =  wellRepo.findById(wellId).get().getField().getConcession().getConcessionName();
                if(jobLocation.equalsIgnoreCase(concessionName)){
                    ThreadLocalToken.getCurrentInstance().close();
                    return true;
                } else {
                    ThreadLocalToken.getCurrentInstance().close();
                    return false;
                }
            } else {
                ThreadLocalToken.getCurrentInstance().close();
                throw new ResourceNotFoundException("No well found with the given id");
            }
        } else {
            threadLocalValue.remove();
            System.out.println("no token, meaning no request with a valid token");
            return false;
        }
    }

    public boolean isWellGenInfoConcessionMember(Integer id){
        String token = ThreadLocalToken.getCurrentInstance().getToken();
        System.out.println(token + "<===============#");
        if(token != null){
            Map claims = jwtUtil.extractAllClaims(token);
            String jobLocation = claims.get("location").toString();
            if(wellGenInfoRepo.findById(id).isPresent()){
                String concessionName = wellGenInfoRepo.findById(id).get().getWell().getField().getConcession().getConcessionName();
                if(jobLocation.equalsIgnoreCase(concessionName)){
                    ThreadLocalToken.getCurrentInstance().close();
                    return true;
                } else {
                    ThreadLocalToken.getCurrentInstance().close();
                    return false;
                }
            } else {
                ThreadLocalToken.getCurrentInstance().close();
                throw new ResourceNotFoundException("No well general info found with the given id");
            }
        } else {
            threadLocalValue.remove();
            System.out.println("no token, meaning no request with a valid token");
            return false;
        }
    }

    public boolean isIntervalsInfoConcessionMember(Integer id){
        String token = ThreadLocalToken.getCurrentInstance().getToken();
        System.out.println(token + "<===============#");
        if(token != null){
            Map claims = jwtUtil.extractAllClaims(token);
            String jobLocation = claims.get("location").toString();
            if(intervalsRepo.findById(id).isPresent()){
                String concessionName = intervalsRepo.findById(id).get().getWell().getField().getConcession().getConcessionName();
                if(jobLocation.equalsIgnoreCase(concessionName)){
                    ThreadLocalToken.getCurrentInstance().close();
                    return true;
                } else {
                    ThreadLocalToken.getCurrentInstance().close();
                    return false;
                }
            } else {
                ThreadLocalToken.getCurrentInstance().close();
                throw new ResourceNotFoundException("No interval found with the given id");
            }
        } else {
            threadLocalValue.remove();
            System.out.println("no token, meaning no request with a valid token");
            return false;
        }
    }

    public boolean isWellConcessionMember(Integer id){
        String token = ThreadLocalToken.getCurrentInstance().getToken();
        System.out.println(token + "<===============#");
        if(token != null){
            Map claims = jwtUtil.extractAllClaims(token);
            String jobLocation = claims.get("location").toString();
            if(fieldRepo.findById(id).isPresent()){
                String concessionName = fieldRepo.findById(id).get().getConcession().getConcessionName();
                if(jobLocation.equalsIgnoreCase(concessionName)){
                    ThreadLocalToken.getCurrentInstance().close();
                    return true;
                } else {
                    ThreadLocalToken.getCurrentInstance().close();
                    return false;
                }
            } else {
                ThreadLocalToken.getCurrentInstance().close();
                throw new ResourceNotFoundException("No well found with the given id");
            }
        } else {
            threadLocalValue.remove();
            System.out.println("no token, meaning no request with a valid token");
            return false;
        }
    }

    public boolean isConcessionMember(Integer id){
        String token = ThreadLocalToken.getCurrentInstance().getToken();
        System.out.println(token + "<===============#");
        if(token != null){
            Map claims = jwtUtil.extractAllClaims(token);
            String jobLocation = claims.get("location").toString();
            if(conRepo.findById(id).isPresent()){
                String concessionName = conRepo.findById(id).get().getConcessionName();
                if(jobLocation.equalsIgnoreCase(concessionName)){
                    ThreadLocalToken.getCurrentInstance().close();
                    return true;
                } else {
                    ThreadLocalToken.getCurrentInstance().close();
                    return false;
                }
            } else {
                ThreadLocalToken.getCurrentInstance().close();
                throw new ResourceNotFoundException("No concession found with the given id");
            }
        } else {
            threadLocalValue.remove();
            System.out.println("no token, meaning no request with a valid token");
            return false;
        }
    }

//    public boolean isFlmConcessionMember(Integer wellId, String email){
//        return getConcessionName(wellId).equalsIgnoreCase(getUserLocationName(email));
//    }

//    public boolean isWellGenInfoConcessionMember(Integer id, String email){
//        String concession = wellGenInfoRepo.findById(id).get().getWell().getField().getConcession().getConcessionName();
//        return concession.equalsIgnoreCase(getUserLocationName(email));
//    }

//    public boolean isIntervalsInfoConcessionMember(Integer id, String email){
//        String concession = intervalsRepo.findById(id).get().getWell().getField().getConcession().getConcessionName();
//        return concession.equalsIgnoreCase(getUserLocationName(email));
//    }

//    public boolean isWellConcessionMember(Integer id, String email){
//        String concession = fieldRepo.findById(id).get().getConcession().getConcessionName();
//        return concession.equalsIgnoreCase(getUserLocationName(email));
//    }

//    public boolean isConcessionMember(Integer id, String email){
//        String concession = conRepo.findById(id).get().getConcessionName();
//        return concession.equalsIgnoreCase(getUserLocationName(email));
//    }

//    public boolean isPBConcessionMember(Integer id, String email){
//        String concession = pbRepo.findById(id).get().getConcession().getConcessionName();
//        return concession.equalsIgnoreCase(getUserLocationName(email));
//    }

}
