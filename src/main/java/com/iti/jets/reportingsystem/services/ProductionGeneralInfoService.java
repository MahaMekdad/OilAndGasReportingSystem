package com.iti.jets.reportingsystem.services;

import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;
import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import com.iti.jets.reportingsystem.models.ProductionGeneralInfoModel;

import java.util.Date;
import java.util.List;

public interface ProductionGeneralInfoService {

    public void delete(int pgiId);

    public void insert(ProductionGeneralInfoModel productionGeneralInfoModel);

    List<ProductionGeneralInfoModel> getAllPGIS();

    List<ProductionGeneralInfoModel> getAllPGIS(Date beginDate, Date endDate);

    public List<ProductionGeneralInfoModel> getAllPGISForAWell(int wellId);

    public List<ProductionGeneralInfoModel> getAllPGISForAWell(int wellId, Date beginDate, Date endDate);

    public void updateSpecificPGIS(int wellId, int pgiId, ProductionGeneralInfoModel productionGeneralInfoModel);

    public void deleteSpecificPGIS(int wellId, int pgiId);
}
