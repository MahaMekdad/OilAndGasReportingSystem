package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import com.iti.jets.reportingsystem.models.ProductionGeneralInfoModel;
import com.iti.jets.reportingsystem.repos.ProductionGeneralInfoRepository;
import com.iti.jets.reportingsystem.repos.WellRepo;
import com.iti.jets.reportingsystem.services.ProductionGeneralInfoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Service
public class ProductionGeneralInfoImpl implements ProductionGeneralInfoService {

    @Autowired
    private ProductionGeneralInfoRepository pgiRepo;

    @Autowired
    private WellRepo wellRepo;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public void delete(int pgiId) {
        pgiRepo .deleteById(pgiId);
    }

    @Override
    public void insert(ProductionGeneralInfoModel productionGeneralInfoModel) {
        System.out.println(productionGeneralInfoModel.getWellId() + " <----------");
        Well well = wellRepo.findById(productionGeneralInfoModel.getWellId()).isPresent() ?
                wellRepo.findById(productionGeneralInfoModel.getWellId()).get() : null;
        if(well == null) {
            System.out.println("no well with given id");
            return;
        }
        ProductionGeneralInfo pgi = new ProductionGeneralInfo();
        pgi.setWell(well);
        pgi.setInitialProduct(productionGeneralInfoModel.getInitialProduct());
        pgi.setInitialProdDate(productionGeneralInfoModel.getInitialProdDate());
        pgi.setInitialType(productionGeneralInfoModel.getInitialType());
        pgi.setInitStatus(productionGeneralInfoModel.getInitStatus());
        pgi.setInitialLiftType(productionGeneralInfoModel.getInitialLiftType());
        pgi.setMonitoringSystem(productionGeneralInfoModel.getMonitoringSystem());
        pgi.setCurrentLiftTypeDate(productionGeneralInfoModel.getCurrentLiftTypeDate());
        pgi.setCurrentLiftType(productionGeneralInfoModel.getCurrentLiftType());
        pgi.setCurrentProduct(productionGeneralInfoModel.getCurrentProduct());
        pgi.setCurrentWellType(productionGeneralInfoModel.getCurrentWellType());
        pgi.setCurrentWellTypeDate(productionGeneralInfoModel.getCurrentWellTypeDate());
        pgi.setRuntime(productionGeneralInfoModel.getRuntime());
        pgi.setPowerSource(productionGeneralInfoModel.getPowerSource());
        pgi.setPowerSourceType(productionGeneralInfoModel.getPowerSourceType());
        pgi.setProcessionPlant(productionGeneralInfoModel.getProcessionPlant());
        pgiRepo.saveAndFlush(pgi);
    }

    @Override
    public List<ProductionGeneralInfoModel> getAllPGIS() {
        Type listType = new TypeToken<List<ProductionGeneralInfoModel>>(){}.getType();
        List<ProductionGeneralInfoModel> resultList;
        resultList = modelMapper.map(pgiRepo.findAll() , listType);
        return resultList;
    }

    @Override
    public List<ProductionGeneralInfoModel> getAllPGIS(Date beginDate, Date endDate) {
        Type listType = new TypeToken<List<ProductionGeneralInfoModel>>(){}.getType();
        List<ProductionGeneralInfoModel> resultList;
        resultList = modelMapper.map(pgiRepo.findAllByInitialProdDateGreaterThanEqualAndInitialProdDateLessThanEqual(beginDate, endDate) , listType);
        return resultList;
    }

    @Override
    public List<ProductionGeneralInfoModel> getAllPGISForAWell(int wellId) {
        Type listType = new TypeToken<List<ProductionGeneralInfoModel>>(){}.getType();
        List<ProductionGeneralInfoModel> resultList;
        resultList = modelMapper.map(pgiRepo.findAllByWell_WellIdEquals(wellId) , listType);
        return resultList;
    }

    @Override
    public List<ProductionGeneralInfoModel> getAllPGISForAWell(int wellId, Date beginDate, Date endDate) {
        Type listType = new TypeToken<List<ProductionGeneralInfoModel>>(){}.getType();
        List<ProductionGeneralInfoModel> resultList;
        resultList = modelMapper.map(pgiRepo.findAllByWell_WellIdEqualsAndInitialProdDateGreaterThanEqualAndInitialProdDateLessThanEqual(wellId, beginDate, endDate) , listType);
        return resultList;
    }

    @Override
    public void updateSpecificPGIS(int wellId, int pgiId, ProductionGeneralInfoModel productionGeneralInfoModel) {
        ProductionGeneralInfo pgiObjToUpdate = pgiRepo.findByWell_WellIdEqualsAndIdEquals(wellId, pgiId);

        if(productionGeneralInfoModel.getInitialProduct() != null){
            pgiObjToUpdate.setInitialProduct(productionGeneralInfoModel.getInitialProduct());
        }
        if(productionGeneralInfoModel.getInitialProdDate() != null){
            pgiObjToUpdate.setInitialProdDate(productionGeneralInfoModel.getInitialProdDate());
        }
        if(productionGeneralInfoModel.getInitialType() != null){
            pgiObjToUpdate.setInitialType(productionGeneralInfoModel.getInitialType());
        }
        if(productionGeneralInfoModel.getInitStatus() != null){
            pgiObjToUpdate.setInitStatus(productionGeneralInfoModel.getInitStatus());
        }
        if(productionGeneralInfoModel.getInitialLiftType() != null){
            pgiObjToUpdate.setInitialLiftType(productionGeneralInfoModel.getInitialLiftType());
        }
        if(productionGeneralInfoModel.getCurrentLiftTypeDate() != null){
            pgiObjToUpdate.setCurrentLiftTypeDate(productionGeneralInfoModel.getCurrentLiftTypeDate());
        }
        if(productionGeneralInfoModel.getMonitoringSystem() != null){
            pgiObjToUpdate.setMonitoringSystem(productionGeneralInfoModel.getMonitoringSystem());
        }
        if(productionGeneralInfoModel.getCurrentLiftType() != null){
            pgiObjToUpdate.setCurrentLiftType(productionGeneralInfoModel.getCurrentLiftType());
        }
        if(productionGeneralInfoModel.getCurrentProduct() != null){
            pgiObjToUpdate.setCurrentProduct(productionGeneralInfoModel.getCurrentProduct());
        }
        if(productionGeneralInfoModel.getCurrentWellType() != null){
            pgiObjToUpdate.setCurrentWellType(productionGeneralInfoModel.getCurrentWellType());
        }
        if(productionGeneralInfoModel.getCurrentWellTypeDate() != null){
            pgiObjToUpdate.setCurrentWellTypeDate(productionGeneralInfoModel.getCurrentWellTypeDate());
        }
        if(productionGeneralInfoModel.getRuntime() != null){
            pgiObjToUpdate.setRuntime(productionGeneralInfoModel.getRuntime());
        }
        if(productionGeneralInfoModel.getPowerSource() != null){
            pgiObjToUpdate.setPowerSource(productionGeneralInfoModel.getPowerSource());
        }
        if(productionGeneralInfoModel.getPowerSourceType() != null){
            pgiObjToUpdate.setPowerSourceType(productionGeneralInfoModel.getPowerSourceType());
        }
        if(productionGeneralInfoModel.getProcessionPlant() != null){
            pgiObjToUpdate.setProcessionPlant(productionGeneralInfoModel.getProcessionPlant());
        }
        pgiRepo.saveAndFlush(pgiObjToUpdate);
    }

    @Override
    public void deleteSpecificPGIS(int wellId, int pgiId) {
        pgiRepo.removeByWell_WellIdEqualsAndIdEquals(wellId, pgiId);
    }
}
