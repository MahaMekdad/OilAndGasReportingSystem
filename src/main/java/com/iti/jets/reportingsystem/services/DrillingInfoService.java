package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.DrillingInfoDataRequest;
import com.iti.jets.openapi.model.DrillingInfoDataResponse;
import com.iti.jets.reportingsystem.entities.DrillingInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DrillingInfoService {
    List<DrillingInfoDataResponse> getAllDrillingInfo();
    List<DrillingInfoDataResponse> getForWellId (int id);
    void creat (DrillingInfoDataRequest drillingInfoModel , int id);
    void delete (int id);
    void deleteWellInSpecificId(int wellId ,int id);
    DrillingInfoDataResponse getWellForId(int wellId , int id);
    void updateWellForId(int wellId , int id , DrillingInfoDataRequest drillingInfoModel );



}
