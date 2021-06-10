package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.AllProductionGeneralInfoWithNamesResponse;
import com.iti.jets.openapi.model.ProductionGeneralInfoRequest;
import com.iti.jets.openapi.model.ProductionGeneralInfoResponse;
import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import com.iti.jets.reportingsystem.models.ProductionGeneralInfoModel;
import com.iti.jets.reportingsystem.repos.ProductionGeneralInfoRepository;
import com.iti.jets.reportingsystem.repos.WellRepo;
import com.iti.jets.reportingsystem.services.ProductionGeneralInfoService;
import com.iti.jets.reportingsystem.utils.mappers.ProductionGeneralInfoMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Service
public class ProductionGeneralInfoImpl implements ProductionGeneralInfoService {

    private ProductionGeneralInfoRepository pgiRepo;

    private WellRepo wellRepo;

    private ModelMapper modelMapper;

    private ProductionGeneralInfoMapper pgiMapper;

    @Autowired
    public ProductionGeneralInfoImpl(ProductionGeneralInfoRepository pgiRepo, WellRepo wellRepo, ModelMapper modelMapper, ProductionGeneralInfoMapper pgiMapper){
        this.pgiRepo = pgiRepo;
        this.wellRepo = wellRepo;
        this.modelMapper = modelMapper;
        this.pgiMapper = pgiMapper;
    }

    @Override
    public void delete(int pgiId) {
        pgiRepo .deleteById(pgiId);
    }

    @Override
    public void insert(ProductionGeneralInfoRequest productionGeneralInfoRequest, int wellId) {
        Well well = wellRepo.findById(wellId).isPresent() ?
                wellRepo.findById(wellId).get() : null;
        if(well == null) {
            System.out.println("no well with given id");
            return;
        }
        ProductionGeneralInfo pgi = pgiMapper.map(productionGeneralInfoRequest);
        pgi.setWell(well);
        pgiRepo.saveAndFlush(pgi);
    }

//    @Override
//    public List<ProductionGeneralInfoModel> getAllPGIS() {
//        Type listType = new TypeToken<List<ProductionGeneralInfoModel>>(){}.getType();
//        List<ProductionGeneralInfoModel> resultList;
//        resultList = modelMapper.map(pgiRepo.findAll() , listType);
//        return resultList;
//    }

    @Override
    public List<AllProductionGeneralInfoWithNamesResponse> getAllPGIS() {
//        Type listType = new TypeToken<List<AllProductionGeneralInfoWithNamesResponse>>(){}.getType();
//        List<AllProductionGeneralInfoWithNamesResponse> resultList;
//        resultList = modelMapper.map(pgiRepo.findAll() , listType);
        List<ProductionGeneralInfo> returnedList = pgiRepo.findAll();
        return pgiMapper.mapPgiList(returnedList);
    }

    @Override
    public List<AllProductionGeneralInfoWithNamesResponse> getAllPGIS(Date beginDate, Date endDate) {
//        Type listType = new TypeToken<List<ProductionGeneralInfoModel>>(){}.getType();
//        List<ProductionGeneralInfoModel> resultList;
//        resultList = modelMapper.map(pgiRepo.findAllByInitialProdDateGreaterThanEqualAndInitialProdDateLessThanEqual(beginDate, endDate) , listType);
        List<ProductionGeneralInfo> returnedList = pgiRepo.findAllByInitialProdDateGreaterThanEqualAndInitialProdDateLessThanEqual(beginDate, endDate);
        return pgiMapper.mapPgiList(returnedList);
    }

    @Override
    public List<ProductionGeneralInfoResponse> getAllPGISForAWell(int wellId) {
        List<ProductionGeneralInfo> returnedList = pgiRepo.findAllByWell_WellIdEquals(wellId) , listType;
        return pgiMapper.mapPgi(returnedList);
    }

    @Override
    public List<ProductionGeneralInfoResponse> getAllPGISForAWellPowerSourceType(int wellId, String powerSourceType) {
        List<ProductionGeneralInfo> returnedList = pgiRepo.findAllByWell_WellIdEqualsAndPowerSourceTypeEquals(wellId, powerSourceType) , listType;
        return pgiMapper.mapPgi(returnedList);
    }

    @Override
    public List<ProductionGeneralInfoResponse> getAllPGISForAWellProcessionPlant(int wellId, String processionPlant) {
        List<ProductionGeneralInfo> returnedList = pgiRepo.findAllByWell_WellIdEqualsAndProcessionPlantEquals(wellId, processionPlant) , listType;
        return pgiMapper.mapPgi(returnedList);
    }

    @Override
    public List<ProductionGeneralInfoResponse> getAllPGISForAWellCurrentWellType(int wellId, String currentWellType) {
        List<ProductionGeneralInfo> returnedList = pgiRepo.findAllByWell_WellIdEqualsAndCurrentWellTypeEquals(wellId, currentWellType) , listType;
        return pgiMapper.mapPgi(returnedList);
    }

    @Override
    public List<ProductionGeneralInfoResponse> getAllPGISForAWellCurrentLiftType(int wellId, String currentLiftType) {
        List<ProductionGeneralInfo> returnedList = pgiRepo.findAllByWell_WellIdEqualsAndCurrentLiftTypeEquals(wellId, currentLiftType) , listType;
        return pgiMapper.mapPgi(returnedList);
    }

    @Override
    public List<ProductionGeneralInfoResponse> getAllPGISForAWellCurrentStatus(int wellId, String currentStatus) {
        List<ProductionGeneralInfo> returnedList = pgiRepo.findAllByWell_WellIdEqualsAndCurrentStatusEquals(wellId, currentStatus) , listType;
        return pgiMapper.mapPgi(returnedList);
    }

//    @Override
//    public List<ProductionGeneralInfoModel> getAllPGISForAWell(int wellId, Date beginDate, Date endDate) {
//        Type listType = new TypeToken<List<ProductionGeneralInfoModel>>(){}.getType();
//        List<ProductionGeneralInfoModel> resultList;
//        resultList = modelMapper.map(pgiRepo.findAllByWell_WellIdEqualsAndInitialProdDateGreaterThanEqualAndInitialProdDateLessThanEqual(wellId, beginDate, endDate) , listType);
//        return resultList;
//    }

    @Override
    public void updateSpecificPGIS(int wellId, int pgiId, ProductionGeneralInfoRequest productionGeneralInfoRequest) {
        ProductionGeneralInfo pgiObjToUpdate = pgiRepo.findByWell_WellIdEqualsAndIdEquals(wellId, pgiId);
        pgiObjToUpdate = pgiMapper.mapForPatch(productionGeneralInfoRequest, pgiObjToUpdate);
        pgiRepo.saveAndFlush(pgiObjToUpdate);
    }

    @Override
    public void deleteSpecificPGIS(int wellId, int pgiId) {
        pgiRepo.removeByWell_WellIdEqualsAndIdEquals(wellId, pgiId);
    }
}
