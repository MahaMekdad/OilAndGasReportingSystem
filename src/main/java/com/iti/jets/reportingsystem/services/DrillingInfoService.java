package com.iti.jets.reportingsystem.services;

import com.iti.jets.reportingsystem.models.DrillingInfoModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DrillingInfoService {
    List<DrillingInfoModel> getAllDrillingInfo();

}
