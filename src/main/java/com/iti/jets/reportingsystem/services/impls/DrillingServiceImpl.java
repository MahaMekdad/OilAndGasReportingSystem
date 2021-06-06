package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.DrillingInfo;
import com.iti.jets.reportingsystem.models.DrillingInfoDto;
import com.iti.jets.reportingsystem.repos.DrillingInfoRepository;
import com.iti.jets.reportingsystem.services.DrillingInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DrillingServiceImpl implements DrillingInfoService {
    @Autowired
    private DrillingInfoRepository drillingInfoRepository;
    @Override
    public List<DrillingInfoDto> getAllDrillingInfo() {

        List<DrillingInfoDto> drillingInfoDtoList = new ArrayList<>();
        drillingInfoDtoList = drillingInfoRepository.findAll();
        return drillingInfoDtoList;
    }

}
