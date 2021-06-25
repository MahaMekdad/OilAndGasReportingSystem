package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.AllProductionGeneralInfoWithNamesResponse;
import com.iti.jets.openapi.model.ProductionGeneralInfoRequest;
import com.iti.jets.openapi.model.ProductionGeneralInfoResponse;
import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.repos.ProductionGeneralInfoRepository;
import com.iti.jets.reportingsystem.repos.WellRepo;
import com.iti.jets.reportingsystem.services.ProductionGeneralInfoService;
import com.iti.jets.reportingsystem.utils.mappers.ProductionGeneralInfoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductionGeneralInfoServiceImpl implements ProductionGeneralInfoService {

    private final ProductionGeneralInfoRepository pgiRepo;
    private final WellRepo wellRepo;
    private ModelMapper modelMapper;
    private final ProductionGeneralInfoMapper pgiMapper;

    @Autowired
    public ProductionGeneralInfoServiceImpl(ProductionGeneralInfoRepository pgiRepo, WellRepo wellRepo, ModelMapper modelMapper, ProductionGeneralInfoMapper pgiMapper){
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
        Well well = wellRepo.findById(wellId).isPresent() ? wellRepo.findById(wellId).get() : null;
        if(well != null) {
            ProductionGeneralInfo pgi = pgiMapper.map(productionGeneralInfoRequest);
            pgi.setWell(well);
            pgiRepo.saveAndFlush(pgi);
        } else {
            throw new ResourceNotFoundException("No PGI/Well found with the given id");
        }
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
        if(wellRepo.findById(wellId).isPresent()){
            List<ProductionGeneralInfo> returnedList = pgiRepo.findAllByWell_WellIdEquals(wellId) , listType;
            return pgiMapper.mapPgi(returnedList);
        } else {
            throw new ResourceNotFoundException("No Well found with the given id");
        }
    }

    @Override
    public List<ProductionGeneralInfoResponse> getAllPGISForAWellPowerSourceType(int wellId, String powerSourceType) {
        if(wellRepo.findById((wellId)).isPresent()){
            List<ProductionGeneralInfo> returnedList = pgiRepo.findAllByWell_WellIdEqualsAndPowerSourceTypeEquals(wellId, powerSourceType) , listType;
            return pgiMapper.mapPgi(returnedList);
        } else {
            throw new ResourceNotFoundException("No Well found with the given id");
        }
    }

    @Override
    public List<ProductionGeneralInfoResponse> getAllPGISForAWellProcessionPlant(int wellId, String processionPlant) {
        if(wellRepo.findById((wellId)).isPresent()){
            List<ProductionGeneralInfo> returnedList = pgiRepo.findAllByWell_WellIdEqualsAndProcessionPlantEquals(wellId, processionPlant) , listType;
            return pgiMapper.mapPgi(returnedList);
        } else {
            throw new ResourceNotFoundException("No Well found with the given id");
        }
    }

    @Override
    public List<ProductionGeneralInfoResponse> getAllPGISForAWellCurrentWellType(int wellId, String currentWellType) {
        if(wellRepo.findById((wellId)).isPresent()){
            List<ProductionGeneralInfo> returnedList = pgiRepo.findAllByWell_WellIdEqualsAndCurrentWellTypeEquals(wellId, currentWellType) , listType;
            return pgiMapper.mapPgi(returnedList);
        } else {
            throw new ResourceNotFoundException("No Well found with the given id");
        }
    }

    @Override
    public List<ProductionGeneralInfoResponse> getAllPGISForAWellCurrentLiftType(int wellId, String currentLiftType) {
        if(wellRepo.findById((wellId)).isPresent()){
            List<ProductionGeneralInfo> returnedList = pgiRepo.findAllByWell_WellIdEqualsAndCurrentLiftTypeEquals(wellId, currentLiftType) , listType;
            return pgiMapper.mapPgi(returnedList);
        } else {
            throw new ResourceNotFoundException("No Well found with the given id");
        }
    }

    @Override
    public List<ProductionGeneralInfoResponse> getAllPGISForAWellCurrentStatus(int wellId, String currentStatus) {

        if(wellRepo.findById((wellId)).isPresent()){
            List<ProductionGeneralInfo> returnedList = pgiRepo.findAllByWell_WellIdEqualsAndCurrentStatusEquals(wellId, currentStatus) , listType;
            return pgiMapper.mapPgi(returnedList);
        } else {
            throw new ResourceNotFoundException("No Well found with the given id");
        }
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
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with this id");
        }
        if(!pgiRepo.findById(pgiId).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with this id");
        }
        if(pgiRepo.findByWell_WellIdEqualsAndIdEquals(wellId, pgiId) != null){
            ProductionGeneralInfo pgiObjToUpdate = pgiRepo.findByWell_WellIdEqualsAndIdEquals(wellId, pgiId);
            pgiObjToUpdate = pgiMapper.mapForPatch(productionGeneralInfoRequest, pgiObjToUpdate);
            pgiRepo.saveAndFlush(pgiObjToUpdate);
        } else {
            throw new ResourceNotFoundException("No PGI/Well found with the given id");
        }
    }

    @Override
    public void deleteSpecificPGIS(int wellId, int pgiId) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with this id");
        }
        if(!pgiRepo.findById(pgiId).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with this id");
        }
        if(pgiRepo.findByWell_WellIdEqualsAndIdEquals(wellId, pgiId) != null){
            pgiRepo.removeByWell_WellIdEqualsAndIdEquals(wellId, pgiId);
        } else {
            throw new ResourceNotFoundException("No PGI/Well found with the given id");
        }
    }
}
