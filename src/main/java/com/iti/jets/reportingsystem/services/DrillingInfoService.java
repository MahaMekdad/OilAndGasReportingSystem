package com.iti.jets.reportingsystem.services;

import com.iti.jets.reportingsystem.entities.DrillingInfo;
import com.iti.jets.reportingsystem.models.DrillingInfoModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DrillingInfoService {
    List<DrillingInfoModel> getAllDrillingInfo();
    DrillingInfoModel getForWellId (int id);
    void creat (DrillingInfoModel drillingInfoModel);
    void delete (int id);
    void update(DrillingInfoModel drillingInfoModel);



}
