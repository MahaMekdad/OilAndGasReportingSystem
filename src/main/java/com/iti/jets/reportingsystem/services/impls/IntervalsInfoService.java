package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.IntervalsInfo;
import com.iti.jets.reportingsystem.entities.WellGeneralInfo;
import com.iti.jets.reportingsystem.repos.IntervalsInfoRepository;
import com.iti.jets.reportingsystem.repos.WellGeneralInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervalsInfoService {
    private IntervalsInfoRepository intervalsInfoRepository;

    @Autowired
    public IntervalsInfoService(IntervalsInfoRepository intervalsInfoRepository){
        this.intervalsInfoRepository=intervalsInfoRepository;
    }

    public List<IntervalsInfo> getAllIntervalsInfo(){
        return intervalsInfoRepository.findAll();
    }

    public IntervalsInfo getIntervalsInfoById(int id){
        if(intervalsInfoRepository.findById(id).isPresent()){
            return intervalsInfoRepository.findById(id).get();
        }
        return null;
    }

    public IntervalsInfo saveIntervalsInfo(IntervalsInfo intervalsInfo){
        return intervalsInfoRepository.save(intervalsInfo);
    }

    public boolean updateIntervalsInfo(int id,IntervalsInfo intervalsInfo){
        if(intervalsInfoRepository.findById(id).isPresent()){
            intervalsInfoRepository.deleteById(id);
            intervalsInfoRepository.save(intervalsInfo);
            return true;
        }
        return false;
    }

    public boolean deleteIntervalsInfo(int id){

        if(intervalsInfoRepository.findById(id).isPresent()){
            intervalsInfoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
