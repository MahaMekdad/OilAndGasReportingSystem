package com.iti.jets.reportingsystem.utils.mappers;

import com.iti.jets.openapi.model.AllProductionGeneralInfoWithNamesResponse;
import com.iti.jets.openapi.model.ProductionGeneralInfoRequest;
import com.iti.jets.openapi.model.ProductionGeneralInfoResponse;
import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProductionGeneralInfoMapper {

    public ProductionGeneralInfo map(ProductionGeneralInfoRequest productionGeneralInfoRequest){
        ProductionGeneralInfo pgi = new ProductionGeneralInfo();
        pgi.setInitialProduct(productionGeneralInfoRequest.getInitialProduct().getValue());
        pgi.setInitialProdDate(Date.from(productionGeneralInfoRequest.getInitialProdDate().toInstant()));
        pgi.setInitialType(productionGeneralInfoRequest.getInitialType().getValue());
        pgi.setInitStatus(productionGeneralInfoRequest.getInitStatus().getValue());
        pgi.setInitialLiftType(productionGeneralInfoRequest.getInitialLiftType().getValue());
        pgi.setMonitoringSystem(productionGeneralInfoRequest.getMonitoringSystem().getValue());
        pgi.setCurrentLiftTypeDate(Date.from(productionGeneralInfoRequest.getCurrentLiftTypeDate().toInstant()));
        pgi.setCurrentLiftType(productionGeneralInfoRequest.getCurrentLiftType().getValue());
        pgi.setCurrentProduct(productionGeneralInfoRequest.getCurrentProduct().getValue());
        pgi.setCurrentWellType(productionGeneralInfoRequest.getCurrentWellType().getValue());
        pgi.setCurrentWellTypeDate(Date.from(productionGeneralInfoRequest.getCurrentWellTypeDate().toInstant()));
        pgi.setCurrentStatus(productionGeneralInfoRequest.getCurrentStatus().getValue());
        pgi.setRuntime(productionGeneralInfoRequest.getRuntime());
        pgi.setPowerSource(productionGeneralInfoRequest.getPowerSource());
        pgi.setPowerSourceType(productionGeneralInfoRequest.getPowerSourceType().getValue());
        System.out.println(productionGeneralInfoRequest.getProcessionPlant().getValue() + " PLEASEEEEEEEEEEEEEEEEEEE");
        pgi.setProcessionPlant(productionGeneralInfoRequest.getProcessionPlant().getValue());
        return pgi;
    }

    public ProductionGeneralInfo mapForPatch(ProductionGeneralInfoRequest productionGeneralInfoRequest, ProductionGeneralInfo pgiObjToUpdate){
        if(productionGeneralInfoRequest.getInitialProduct() != null){
            pgiObjToUpdate.setInitialProduct(productionGeneralInfoRequest.getInitialProduct().getValue());
        }
        if(productionGeneralInfoRequest.getInitialProdDate() != null){
            pgiObjToUpdate.setInitialProdDate(Date.from(productionGeneralInfoRequest.getInitialProdDate().toInstant()));
        }
        if(productionGeneralInfoRequest.getInitialType() != null){
            pgiObjToUpdate.setInitialType(productionGeneralInfoRequest.getInitialType().getValue());
        }
        if(productionGeneralInfoRequest.getInitStatus() != null){
            pgiObjToUpdate.setInitStatus(productionGeneralInfoRequest.getInitStatus().getValue());
        }
        if(productionGeneralInfoRequest.getInitialLiftType() != null){
            pgiObjToUpdate.setInitialLiftType(productionGeneralInfoRequest.getInitialLiftType().getValue());
        }
        if(productionGeneralInfoRequest.getCurrentLiftTypeDate() != null){
            pgiObjToUpdate.setCurrentLiftTypeDate(Date.from(productionGeneralInfoRequest.getCurrentLiftTypeDate().toInstant()));
        }
        if(productionGeneralInfoRequest.getMonitoringSystem() != null){
            pgiObjToUpdate.setMonitoringSystem(productionGeneralInfoRequest.getMonitoringSystem().getValue());
        }
        if(productionGeneralInfoRequest.getCurrentLiftType() != null){
            pgiObjToUpdate.setCurrentLiftType(productionGeneralInfoRequest.getCurrentLiftType().getValue());
        }
        if(productionGeneralInfoRequest.getCurrentProduct() != null){
            pgiObjToUpdate.setCurrentProduct(productionGeneralInfoRequest.getCurrentProduct().getValue());
        }
        if(productionGeneralInfoRequest.getCurrentWellType() != null){
            pgiObjToUpdate.setCurrentWellType(productionGeneralInfoRequest.getCurrentWellType().getValue());
        }
        if(productionGeneralInfoRequest.getCurrentWellTypeDate() != null){
            pgiObjToUpdate.setCurrentWellTypeDate(Date.from(productionGeneralInfoRequest.getCurrentWellTypeDate().toInstant()));
        }
        if(productionGeneralInfoRequest.getRuntime() != null){
            pgiObjToUpdate.setRuntime(productionGeneralInfoRequest.getRuntime());
        }
        if(productionGeneralInfoRequest.getPowerSource() != null){
            pgiObjToUpdate.setPowerSource(productionGeneralInfoRequest.getPowerSource());
        }
        if(productionGeneralInfoRequest.getPowerSourceType() != null){
            pgiObjToUpdate.setPowerSourceType(productionGeneralInfoRequest.getPowerSourceType().getValue());
        }
        if(productionGeneralInfoRequest.getProcessionPlant() != null){
            pgiObjToUpdate.setProcessionPlant(productionGeneralInfoRequest.getProcessionPlant().getValue());
        }
        return pgiObjToUpdate;
    }

    public List<AllProductionGeneralInfoWithNamesResponse> mapPgiList(List<ProductionGeneralInfo> targetList){
        List<AllProductionGeneralInfoWithNamesResponse> resultList = new ArrayList<>();
        for (ProductionGeneralInfo pgi : targetList) {
            AllProductionGeneralInfoWithNamesResponse apgiwnr = new AllProductionGeneralInfoWithNamesResponse();
            apgiwnr.setId(pgi.getId());
            apgiwnr.setMonitoringSystem(AllProductionGeneralInfoWithNamesResponse.MonitoringSystemEnum.valueOf(pgi.getMonitoringSystem().toUpperCase()));
            apgiwnr.setCurrentProduct(AllProductionGeneralInfoWithNamesResponse.CurrentProductEnum.valueOf(pgi.getCurrentProduct().toUpperCase()));
            apgiwnr.setCurrentLiftType(AllProductionGeneralInfoWithNamesResponse.CurrentLiftTypeEnum.valueOf(pgi.getCurrentLiftType().toUpperCase()));
            apgiwnr.setCurrentLiftTypeDate(dateHelper(pgi.getCurrentLiftTypeDate()));
            apgiwnr.setCurrentWellType(AllProductionGeneralInfoWithNamesResponse.CurrentWellTypeEnum.valueOf(pgi.getCurrentWellType().toUpperCase().replace(" ", "_")));
            apgiwnr.setCurrentWellTypeDate(dateHelper(pgi.getCurrentWellTypeDate()));
            apgiwnr.setCurrentStatus(AllProductionGeneralInfoWithNamesResponse.CurrentStatusEnum.valueOf(pgi.getCurrentStatus().toUpperCase())) ;
            apgiwnr.setInitialProduct(AllProductionGeneralInfoWithNamesResponse.InitialProductEnum.valueOf(pgi.getInitialProduct().toUpperCase()));
            apgiwnr.setInitialProdDate(dateHelper(pgi.getInitialProdDate()));
            apgiwnr.setInitialLiftType(AllProductionGeneralInfoWithNamesResponse.InitialLiftTypeEnum.valueOf(pgi.getInitialLiftType().toUpperCase()));
            apgiwnr.setInitialType(AllProductionGeneralInfoWithNamesResponse.InitialTypeEnum.valueOf(pgi.getInitialType().toUpperCase().replace(" ", "_")));
            apgiwnr.setInitStatus(AllProductionGeneralInfoWithNamesResponse.InitStatusEnum.valueOf(pgi.getInitStatus().toUpperCase()));
            apgiwnr.setRuntime(pgi.getRuntime());
            apgiwnr.setPowerSource(pgi.getPowerSource());
            apgiwnr.setPowerSourceType(AllProductionGeneralInfoWithNamesResponse.PowerSourceTypeEnum.valueOf(pgi.getPowerSourceType().toUpperCase()));
            apgiwnr.setProcessionPlant(AllProductionGeneralInfoWithNamesResponse.ProcessionPlantEnum.valueOf(pgi.getProcessionPlant().toUpperCase().replace(" ", "_")));
            apgiwnr.setWellName(pgi.getWell().getWellName());
            apgiwnr.setFieldName(pgi.getWell().getField().getFieldName());
            apgiwnr.setConcessionName(pgi.getWell().getField().getConcession().getConcessionName());
            resultList.add(apgiwnr);
        }
        return resultList;
    }

    public List<ProductionGeneralInfoResponse> mapPgi(List<ProductionGeneralInfo> targetList){
        List<ProductionGeneralInfoResponse> resultList = new ArrayList<>();
        for (ProductionGeneralInfo pgi : targetList) {
            ProductionGeneralInfoResponse pgir = new ProductionGeneralInfoResponse();
            pgir.setId(pgi.getId());
            pgir.setMonitoringSystem(ProductionGeneralInfoResponse.MonitoringSystemEnum.valueOf(pgi.getMonitoringSystem().toUpperCase()));
            pgir.setCurrentProduct(ProductionGeneralInfoResponse.CurrentProductEnum.valueOf(pgi.getCurrentProduct().toUpperCase()));
            pgir.setCurrentLiftType(ProductionGeneralInfoResponse.CurrentLiftTypeEnum.valueOf(pgi.getCurrentLiftType().toUpperCase()));
            pgir.setCurrentLiftTypeDate(dateHelper(pgi.getCurrentLiftTypeDate()));
            pgir.setCurrentWellType(ProductionGeneralInfoResponse.CurrentWellTypeEnum.valueOf(pgi.getCurrentWellType().toUpperCase().replace(" ", "_")));
            pgir.setCurrentWellTypeDate(dateHelper(pgi.getCurrentWellTypeDate()));
            pgir.setCurrentStatus(ProductionGeneralInfoResponse.CurrentStatusEnum.valueOf(pgi.getCurrentStatus().toUpperCase())) ;
            pgir.setInitialProduct(ProductionGeneralInfoResponse.InitialProductEnum.valueOf(pgi.getInitialProduct().toUpperCase()));
            pgir.setInitialProdDate(dateHelper(pgi.getInitialProdDate()));
            pgir.setInitialLiftType(ProductionGeneralInfoResponse.InitialLiftTypeEnum.valueOf(pgi.getInitialLiftType().toUpperCase()));
            pgir.setInitialType(ProductionGeneralInfoResponse.InitialTypeEnum.valueOf(pgi.getInitialType().toUpperCase().replace(" ", "_")));
            pgir.setInitStatus(ProductionGeneralInfoResponse.InitStatusEnum.valueOf(pgi.getInitStatus().toUpperCase()));
            pgir.setRuntime(pgi.getRuntime());
            pgir.setPowerSource(pgi.getPowerSource());
            pgir.setPowerSourceType(ProductionGeneralInfoResponse.PowerSourceTypeEnum.valueOf(pgi.getPowerSourceType().toUpperCase()));
            pgir.setProcessionPlant(ProductionGeneralInfoResponse.ProcessionPlantEnum.valueOf(pgi.getProcessionPlant().toUpperCase().replace(" ", "_")));
            resultList.add(pgir);
        }
        return resultList;
    }

    public OffsetDateTime dateHelper(Date dateToConvert){
        LocalDate localDate = new java.sql.Date(dateToConvert.getTime()).toLocalDate();
        return localDate.atTime(0,0,0).atOffset(ZoneOffset.UTC);
    }
}
