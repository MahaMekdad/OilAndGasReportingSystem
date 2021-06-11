package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.IntervalsInfoRequest;
import com.iti.jets.openapi.model.IntervalsInfoResponse;
import com.iti.jets.openapi.model.WellGeneralInfoResponse;
import com.iti.jets.reportingsystem.entities.IntervalsInfo;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.entities.WellGeneralInfo;
import com.iti.jets.reportingsystem.repos.IntervalsInfoRepository;
import com.iti.jets.reportingsystem.repos.WellGeneralInfoRepository;
import com.iti.jets.reportingsystem.repos.WellRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IntervalsInfoService {
    private IntervalsInfoRepository intervalsInfoRepository;
    private ModelMapper mapper;
    private WellRespository wellRespository;
    @Autowired
    public IntervalsInfoService(IntervalsInfoRepository intervalsInfoRepository,ModelMapper modelMapper,
         WellRespository wellRespository){
        this.intervalsInfoRepository=intervalsInfoRepository;
        this.mapper=modelMapper;
        this.wellRespository=wellRespository;
    }

    public List<IntervalsInfoResponse> getAllIntervalsInfo(){
        List<IntervalsInfoResponse> intervalsInfoResponseList=new ArrayList<>();
        Iterable<IntervalsInfo> wellGeneralInfoIterable=intervalsInfoRepository.findAll();
        wellGeneralInfoIterable.forEach((intervalsInfo)->{
            intervalsInfoResponseList.add(mapper.map(intervalsInfo,IntervalsInfoResponse.class));
        });

        return intervalsInfoResponseList;

    }

    public IntervalsInfoResponse getIntervalsInfoById(int id){
        if(intervalsInfoRepository.findById(id).isPresent()){
            return mapper.map(intervalsInfoRepository.findById(id).get(),IntervalsInfoResponse.class);
        }
        return null;
    }

    public IntervalsInfo saveIntervalsInfo(IntervalsInfoRequest intervalsInfoRequest){
        IntervalsInfo intervalsInfo=mapper.map(intervalsInfoRequest,IntervalsInfo.class);
        Well well=wellRespository.getById(intervalsInfoRequest.getWellId());
        intervalsInfo.setId(null);
        if(well == null) {
            return null;
        }
        intervalsInfo.setWell(well);
        return intervalsInfoRepository.save(intervalsInfo);

    }

    public boolean updateIntervalsInfo(int id,IntervalsInfoRequest intervalsInfoRequest){
        if(intervalsInfoRepository.findById(id).isPresent()){
            IntervalsInfo intervalsInfo=mapper.map(intervalsInfoRequest,IntervalsInfo.class);
            Well well=wellRespository.getById(intervalsInfoRequest.getWellId());
            if(well == null){
                return false;
            }
            intervalsInfo.setWell(well);
            intervalsInfo.setId(id);
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
