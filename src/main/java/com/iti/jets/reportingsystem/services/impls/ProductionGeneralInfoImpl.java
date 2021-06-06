package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;
import com.iti.jets.reportingsystem.repos.ProductionGeneralInfoRepository;
import com.iti.jets.reportingsystem.services.ProductionGeneralInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionGeneralInfoImpl implements ProductionGeneralInfoService {

    @Autowired
    private ProductionGeneralInfoRepository pgiRepo;

    @Override
    public void delete(int pgiId) {
        pgiRepo.deleteById(pgiId);
    }

    @Override
    public void insert(ProductionGeneralInfo productionGeneralInfo) {
        pgiRepo.saveAndFlush(productionGeneralInfo);
    }

    @Override
    public List<ProductionGeneralInfo> getAllPGISForAWell(int wellId) {
//        return pgiRepo.findAllByWell_WellIdEquals(wellId);
        return pgiRepo.findAll();
    }

    @Override
    public void updateSpecificPGIS(int wellId, int pgiId, ProductionGeneralInfo productionGeneralInfo) {

    }

    @Override
    public void deleteSpecificPGIS(int wellId, int pgiId) {

    }
}
