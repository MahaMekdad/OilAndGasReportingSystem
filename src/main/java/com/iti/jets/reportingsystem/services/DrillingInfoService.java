package com.iti.jets.reportingsystem.services;

import com.iti.jets.reportingsystem.entities.DrillingInfo;
import com.iti.jets.reportingsystem.models.DrillingInfoModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DrillingInfoService {
    List<DrillingInfoModel> getAllDrillingInfo();
    List<DrillingInfoModel> getForWellId (int id);
    void creat (DrillingInfoModel drillingInfoModel , int id);
    void delete (int id);
    void deleteWellInSpecificId(int wellId ,int id);
    DrillingInfoModel getWellForId(int wellId , int id);
    void updateWellForId(int wellId , int id ,DrillingInfoModel drillingInfoModel );



}
