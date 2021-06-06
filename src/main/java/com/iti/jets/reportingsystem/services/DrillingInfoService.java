package com.iti.jets.reportingsystem.services;

import com.iti.jets.reportingsystem.entities.DrillingInfo;

import java.util.List;

public interface DrillingInfoService {
    List<DrillingInfo> getAllDrillingInfo();
    DrillingInfo getWellDrillingInfo(int wellId);

}
