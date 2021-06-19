package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.WellGeneralInfoRequest;
import com.iti.jets.openapi.model.WellGeneralInfoResponse;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.entities.WellGeneralInfo;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.repos.WellGeneralInfoRepository;
import com.iti.jets.reportingsystem.repos.WellRespository;
import com.iti.jets.reportingsystem.services.WellGeneralInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class WellGeneralInfoServiceImpl implements WellGeneralInfoService {
    private WellGeneralInfoRepository wellGeneralInfoRepository;
    private WellRespository wellRespository;
    private ModelMapper mapper;
    @Autowired
    public WellGeneralInfoServiceImpl(WellGeneralInfoRepository wellGeneralInfoRepository, ModelMapper modelMapper
            , WellRespository wellRespository){
        this.wellGeneralInfoRepository=wellGeneralInfoRepository;
        this.mapper=modelMapper;
        this.wellRespository=wellRespository;
        mapper.getConfiguration().setAmbiguityIgnored(true);

    }

    public List<WellGeneralInfoResponse> getAllWellsGeneralInfo(){
        List<WellGeneralInfoResponse> wellGeneralInfoResponseList=new ArrayList<>();
        Iterable<WellGeneralInfo> wellGeneralInfoIterable=(Iterable<WellGeneralInfo>) wellGeneralInfoRepository.findAll();
        wellGeneralInfoIterable.forEach((wellGeneralInfo)->{
            wellGeneralInfoResponseList.add(mapper.map(wellGeneralInfo,WellGeneralInfoResponse.class));
        });

        return wellGeneralInfoResponseList;
    }




    public WellGeneralInfoResponse getWellGeneralInfoById(int wellId){
        if(wellRespository.findById(wellId).isPresent()){
            WellGeneralInfo wellGeneralInfo =wellGeneralInfoRepository.findWellGeneralInfoByWellIs(wellRespository.findById(wellId).get());
            return mapper.map(wellGeneralInfo,WellGeneralInfoResponse.class);
        }
        else
        {
            throw new ResourceNotFoundException("There is no well with the given id");
        }

    }

    public WellGeneralInfo saveWellGeneralInfo(WellGeneralInfoRequest wellGeneralInfoRequest){
            WellGeneralInfo wellGeneralInfo=mapper.map(wellGeneralInfoRequest,WellGeneralInfo.class);
            Well well=wellRespository.getById(wellGeneralInfoRequest.getWellId());
            if(well == null) {
              return null;
            }
            wellGeneralInfo.setWell(well);
            return wellGeneralInfoRepository.save(wellGeneralInfo);
    }

    public boolean updateWellGeneralInfo(int id,WellGeneralInfoRequest wellGeneralInfoRequest){
        if(wellGeneralInfoRepository.findById(id).isPresent()){
            WellGeneralInfo wellGeneralInfo=mapper.map(wellGeneralInfoRequest,WellGeneralInfo.class);
            Well well=wellRespository.getById(wellGeneralInfoRequest.getWellId());
            if(well == null){
                return false;
            }
            wellGeneralInfo.setWell(well);
            wellGeneralInfo.setId(id);
            wellGeneralInfoRepository.save(wellGeneralInfo);
            return true;
        }

        else
        {
            throw new ResourceNotFoundException("There is record found with the given id");
        }
    }

    public boolean deleteWellGeneralInfo(int id){
        if(wellGeneralInfoRepository.findById(id).isPresent()){
            wellGeneralInfoRepository.deleteById(id);
            return true;
        }
       else
        {
            throw new ResourceNotFoundException("There is no record with this id");
        }
    }


}
