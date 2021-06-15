package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.WellsApi;
import com.iti.jets.openapi.model.AllProductionGeneralInfoWithNamesResponse;
import com.iti.jets.openapi.model.ProductionGeneralInfoRequest;
import com.iti.jets.openapi.model.ProductionGeneralInfoResponse;
import com.iti.jets.reportingsystem.services.ProductionGeneralInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

//@RestController
public class ProductionGeneralInfoController implements WellsApi {

    private ProductionGeneralInfoService pgiService;

    @Autowired
    public ProductionGeneralInfoController(ProductionGeneralInfoService pgiService){
        this.pgiService = pgiService;
    }

    //get all in gen
    @Override
    public ResponseEntity<List<AllProductionGeneralInfoWithNamesResponse>> wellsProductionGeneralInfoGet() {
        return ResponseEntity.ok(pgiService.getAllPGIS());
    }

    //get for a specific well
    @Override
    public ResponseEntity<List<ProductionGeneralInfoResponse>> wellsWellIdProductionGeneralInfoGet(Integer wellId, @Valid String powerSourceType, @Valid String processionPlant, @Valid String currentWellType, @Valid String currentLiftType, @Valid String currentStatus) {
//        return ResponseEntity.ok(pgiService.getAllPGIS(beginDate, endDate));
        return null;
    }

    @Override
    public ResponseEntity<Void> wellsWellIdProductionGeneralInfoPost(Integer wellId, @Valid ProductionGeneralInfoRequest productionGeneralInfoRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> wellsWellIdProductionGeneralInfoPgiIdPut(Integer wellId, Integer pgiId, @Valid ProductionGeneralInfoRequest productionGeneralInfoRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> wellsWellIdProductionGeneralInfoPgiIdDelete(Integer wellId, Integer pgiId) {
        return null;
    }
}
