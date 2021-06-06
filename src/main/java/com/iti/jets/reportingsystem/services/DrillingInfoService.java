package com.iti.jets.reportingsystem.services;

import com.iti.jets.reportingsystem.entities.DrillingInfo;
import com.iti.jets.reportingsystem.models.DrillingInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DrillingInfoService {
    List<DrillingInfoDto> getAllDrillingInfo();

}
