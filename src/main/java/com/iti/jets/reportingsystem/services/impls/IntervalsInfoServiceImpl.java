package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.IntervalsInfoRequest;
import com.iti.jets.openapi.model.IntervalsInfoResponse;
import com.iti.jets.reportingsystem.entities.IntervalsInfo;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.repos.IntervalsInfoRepository;
import com.iti.jets.reportingsystem.repos.WellRespository;
import com.iti.jets.reportingsystem.services.IntervalsInfoService;
import com.iti.jets.reportingsystem.utils.mappers.helpers.OffsetDateTimeHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IntervalsInfoServiceImpl implements IntervalsInfoService {
    private IntervalsInfoRepository intervalsInfoRepository;
    private ModelMapper mapper;
    private WellRespository wellRespository;

    @Autowired
    public IntervalsInfoServiceImpl(IntervalsInfoRepository intervalsInfoRepository, ModelMapper modelMapper,
                                    WellRespository wellRespository) {
        this.intervalsInfoRepository = intervalsInfoRepository;
        this.mapper = modelMapper;
        this.wellRespository = wellRespository;
    }

    public List<IntervalsInfoResponse> getAllIntervalsInfo() {
        List<IntervalsInfoResponse> intervalsInfoResponseList = new ArrayList<>();
        Iterable<IntervalsInfo> wellGeneralInfoIterable = intervalsInfoRepository.findAll();
        wellGeneralInfoIterable.forEach((intervalsInfo) -> {
            IntervalsInfoResponse intervalsInfoResponse = mapper.map(intervalsInfo, IntervalsInfoResponse.class);
            if(intervalsInfo.getDrivingMechanism()!=null) {
                intervalsInfoResponse.setDrivingMechanism(IntervalsInfoResponse.DrivingMechanismEnum.valueOf(intervalsInfo.getDrivingMechanism().toUpperCase()));
            }
            if(intervalsInfo.getStatus()!=null) {
                intervalsInfoResponse.setStatus(IntervalsInfoResponse.StatusEnum.valueOf(intervalsInfo.getStatus().toUpperCase()));
            }
            if(intervalsInfo.getStartDate()!=null) {
                intervalsInfoResponse.setStartDate(OffsetDateTimeHelper.dateHelper(intervalsInfo.getStartDate()));
            }
            if(intervalsInfo.getEndDate()!=null) {
                intervalsInfoResponse.setEndDate(OffsetDateTimeHelper.dateHelper(intervalsInfo.getEndDate()));
            }
            intervalsInfoResponseList.add(intervalsInfoResponse);
        });

        return intervalsInfoResponseList;

    }

    public IntervalsInfoResponse getIntervalsInfoById(int id) {
        if(!intervalsInfoRepository.findById(id).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with this id");
        }
        IntervalsInfo intervalsInfo = intervalsInfoRepository.findById(id).get();
        if (intervalsInfo != null) {
            IntervalsInfoResponse intervalsInfoResponse = mapper.map(intervalsInfoRepository.findById(id).get(), IntervalsInfoResponse.class);
            if(intervalsInfo.getDrivingMechanism()!=null) {
                intervalsInfoResponse.setDrivingMechanism(IntervalsInfoResponse.DrivingMechanismEnum.valueOf(intervalsInfo.getDrivingMechanism().toUpperCase()));
            }
            if(intervalsInfo.getStatus()!=null) {
                intervalsInfoResponse.setStatus(IntervalsInfoResponse.StatusEnum.valueOf(intervalsInfo.getStatus().toUpperCase()));
            }
            if(intervalsInfo.getStartDate()!=null) {
                intervalsInfoResponse.setStartDate(OffsetDateTimeHelper.dateHelper(intervalsInfo.getStartDate()));
            }
            if(intervalsInfo.getEndDate()!=null) {
                intervalsInfoResponse.setEndDate(OffsetDateTimeHelper.dateHelper(intervalsInfo.getEndDate()));
            }
            return intervalsInfoResponse;

        }
        return null;
    }

    public IntervalsInfo saveIntervalsInfo(IntervalsInfoRequest intervalsInfoRequest) {
        System.out.println("interval request " + intervalsInfoRequest);
        IntervalsInfo intervalsInfo = mapper.map(intervalsInfoRequest, IntervalsInfo.class);
        intervalsInfo.setStartDate(Date.from(intervalsInfoRequest.getStartDate().toInstant()));
        intervalsInfo.setEndDate(Date.from(intervalsInfoRequest.getEndDate().toInstant()));
        Well well = wellRespository.getById(intervalsInfoRequest.getWellId());
        intervalsInfo.setId(null);
        if (well == null) {
            return null;
        }
        intervalsInfo.setWell(well);
        return intervalsInfoRepository.save(intervalsInfo);

    }

    public boolean updateIntervalsInfo(int id, IntervalsInfoRequest intervalsInfoRequest) {
        if(!intervalsInfoRepository.findById(id).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with this id");
        }
        if (intervalsInfoRepository.findById(id).isPresent()) {
            IntervalsInfo intervalsInfo = mapper.map(intervalsInfoRequest, IntervalsInfo.class);

            intervalsInfo.setStartDate(Date.from(intervalsInfoRequest.getStartDate().toInstant()));
            System.out.println("StartDate == " + intervalsInfo.getStartDate());
            intervalsInfo.setEndDate(Date.from(intervalsInfoRequest.getEndDate().toInstant()));
            Well well = wellRespository.getById(intervalsInfoRequest.getWellId());
            if (well == null) {
                return false;
            }
            intervalsInfo.setWell(well);
            intervalsInfo.setId(id);
            intervalsInfoRepository.save(intervalsInfo);
            return true;
        }
        return false;

    }

    public boolean deleteIntervalsInfo(int id) {
         if(!intervalsInfoRepository.findById(id).isPresent())
         {
             throw new ResourceNotFoundException("There is no record with this id");
         }
        if (intervalsInfoRepository.findById(id).isPresent()) {
            intervalsInfoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
