package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.WellGeneralInfo;
import com.iti.jets.reportingsystem.repos.WellGeneralInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WellGeneralInfoService {
    private WellGeneralInfoRepository wellGeneralInfoRepository;

    @Autowired
    public WellGeneralInfoService(WellGeneralInfoRepository wellGeneralInfoRepository){
        this.wellGeneralInfoRepository=wellGeneralInfoRepository;
    }

    public List<WellGeneralInfo> getAllWellsGeneralInfo(){
        return wellGeneralInfoRepository.findAll();
    }

    public WellGeneralInfo getWellGeneralInfoById(int id){
        if(wellGeneralInfoRepository.findById(id).isPresent()){
            return wellGeneralInfoRepository.findById(id).get();
        }
        return null;
    }

    public WellGeneralInfo saveWellGeneralInfo(WellGeneralInfo wellGeneralInfo){
            return wellGeneralInfoRepository.save(wellGeneralInfo);
    }

    public boolean updateWellGeneralInfo(int id,WellGeneralInfo wellGeneralInfo){
        if(wellGeneralInfoRepository.findById(id).isPresent()){
            wellGeneralInfoRepository.deleteById(id);
            wellGeneralInfoRepository.save(wellGeneralInfo);
            return true;
        }
        return false;
    }

    public boolean deleteWellGeneralInfo(int id){

        if(wellGeneralInfoRepository.findById(id).isPresent()){
            wellGeneralInfoRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
