package com.iti.jets.reportingsystem.services;

import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;

import java.util.List;

public interface ProductionGeneralInfoService {

    public void delete(int pgiId);

    public void insert(ProductionGeneralInfo productionGeneralInfo);

    public List<ProductionGeneralInfo> getAllPGISForAWell(int wellId);

    public void updateSpecificPGIS(int wellId, int pgiId, ProductionGeneralInfo productionGeneralInfo);

    public void deleteSpecificPGIS(int wellId, int pgiId);
}
