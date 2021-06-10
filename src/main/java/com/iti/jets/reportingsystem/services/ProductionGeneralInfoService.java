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

    public void delete(int pgiId);

//    public void insert(ProductionGeneralInfoModel productionGeneralInfoModel);

    public void insert(ProductionGeneralInfoRequest productionGeneralInfoRequest, int wellId);

//    List<ProductionGeneralInfoModel> getAllPGIS();
    List<AllProductionGeneralInfoWithNamesResponse> getAllPGIS();

//    List<ProductionGeneralInfoModel> getAllPGIS(Date beginDate, Date endDate);
    List<AllProductionGeneralInfoWithNamesResponse> getAllPGIS(Date beginDate, Date endDate);

//    public List<ProductionGeneralInfoModel> getAllPGISForAWell(int wellId);

    public List<ProductionGeneralInfoResponse> getAllPGISForAWell(int wellId);

    public List<ProductionGeneralInfoResponse> getAllPGISForAWellPowerSourceType(int wellId, String powerSourceType);

    public List<ProductionGeneralInfoResponse> getAllPGISForAWellProcessionPlant(int wellId, String processionPlant);

    public List<ProductionGeneralInfoResponse> getAllPGISForAWellCurrentWellType(int wellId, String currentWellType);

    public List<ProductionGeneralInfoResponse> getAllPGISForAWellCurrentLiftType(int wellId, String CurrentLiftType);

    public List<ProductionGeneralInfoResponse> getAllPGISForAWellCurrentStatus(int wellId, String CurrentStatus);

//    public List<ProductionGeneralInfoModel> getAllPGISForAWell(int wellId, Date beginDate, Date endDate);

    public void updateSpecificPGIS(int wellId, int pgiId, ProductionGeneralInfoRequest productionGeneralInfoRequest);

    public void deleteSpecificPGIS(int wellId, int pgiId);
}
