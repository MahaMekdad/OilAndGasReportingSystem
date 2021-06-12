package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.AllProductionGeneralInfoWithNamesResponse;
import com.iti.jets.openapi.model.ProductionGeneralInfoRequest;
import com.iti.jets.openapi.model.ProductionGeneralInfoResponse;
import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;
import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import com.iti.jets.reportingsystem.models.ProductionGeneralInfoModel;

import java.util.Date;
import java.util.List;

public interface ProductionGeneralInfoService {

    void delete(int pgiId);

//    void insert(ProductionGeneralInfoModel productionGeneralInfoModel);

    void insert(ProductionGeneralInfoRequest productionGeneralInfoRequest, int wellId);

//    List<ProductionGeneralInfoModel> getAllPGIS();
    List<AllProductionGeneralInfoWithNamesResponse> getAllPGIS();

//    List<ProductionGeneralInfoModel> getAllPGIS(Date beginDate, Date endDate);
    List<AllProductionGeneralInfoWithNamesResponse> getAllPGIS(Date beginDate, Date endDate);

//    List<ProductionGeneralInfoModel> getAllPGISForAWell(int wellId);

    List<ProductionGeneralInfoResponse> getAllPGISForAWell(int wellId);

    List<ProductionGeneralInfoResponse> getAllPGISForAWellPowerSourceType(int wellId, String powerSourceType);

    List<ProductionGeneralInfoResponse> getAllPGISForAWellProcessionPlant(int wellId, String processionPlant);

    List<ProductionGeneralInfoResponse> getAllPGISForAWellCurrentWellType(int wellId, String currentWellType);

    List<ProductionGeneralInfoResponse> getAllPGISForAWellCurrentLiftType(int wellId, String CurrentLiftType);

    List<ProductionGeneralInfoResponse> getAllPGISForAWellCurrentStatus(int wellId, String CurrentStatus);

//    List<ProductionGeneralInfoModel> getAllPGISForAWell(int wellId, Date beginDate, Date endDate);

    void updateSpecificPGIS(int wellId, int pgiId, ProductionGeneralInfoRequest productionGeneralInfoRequest);

    void deleteSpecificPGIS(int wellId, int pgiId);
}
