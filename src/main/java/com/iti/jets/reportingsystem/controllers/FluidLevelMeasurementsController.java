package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.WellsApi;
import com.iti.jets.openapi.model.*;
import com.iti.jets.reportingsystem.services.DrillingInfoService;
import com.iti.jets.reportingsystem.services.FluidLevelMeasurementsService;
import com.iti.jets.reportingsystem.services.ProductionGeneralInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class FluidLevelMeasurementsController implements WellsApi {


    private final FluidLevelMeasurementsService flmService;
    private final ProductionGeneralInfoService pgiService;
    private final DrillingInfoService drillingInfoService;

    @Autowired
    public FluidLevelMeasurementsController(FluidLevelMeasurementsService flmService, ProductionGeneralInfoService pgiService, DrillingInfoService drillingInfoService){
        this.flmService = flmService;
        this.pgiService = pgiService;
        this.drillingInfoService = drillingInfoService;
    }

//    ######################FluidLevelMeasurements#########################

    //get all in gen
    @Override
    public ResponseEntity<List<AllFluidLevelMeasurementResponse>> wellsFluidLevelMeasurementsGet(@Valid OffsetDateTime beginDate, @Valid OffsetDateTime endDate) {
        if(beginDate != null && endDate != null){
            Date begin = Date.from(beginDate.toInstant());
            System.out.println(begin + " <== begin");
            Date end = Date.from(endDate.toInstant());
            System.out.println(end + " <== end");
            return ResponseEntity.ok(flmService.getAllFLMS(begin, end));
        } else {
            return ResponseEntity.ok(flmService.getAllFLMS());
        }
    }

    //get all for a specific well
    @Override
    public ResponseEntity<List<FluidLevelMeasurementResponse>> wellsWellIdFluidLevelMeasurementsGet(Integer wellId, @Valid OffsetDateTime beginDate, @Valid OffsetDateTime endDate) {
        if(beginDate != null && endDate != null){
            Date begin = Date.from(beginDate.toInstant());
            System.out.println(begin + " <== begin");
            Date end = Date.from(endDate.toInstant());
            System.out.println(end + " <== end");
            return ResponseEntity.ok(flmService.getAllFLMSForAWell(wellId, begin, end));
        } else {
            return ResponseEntity.ok(flmService.getAllFLMSForAWell(wellId));
        }
    }

    @Override
    public ResponseEntity<Void> wellsWellIdFluidLevelMeasurementsPost(Integer wellId, @Valid FluidLevelMeasurementRequest fluidLevelMeasurementRequest) {
        flmService.insert(fluidLevelMeasurementRequest, wellId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> wellsWellIdFluidLevelMeasurementsFlmIdPatch(Integer wellId, Integer flmId, @Valid FluidLevelMeasurementRequest fluidLevelMeasurementRequest) {
        flmService.updateSpecificFLMS(wellId, flmId, fluidLevelMeasurementRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> wellsWellIdFluidLevelMeasurementsFlmIdPut(Integer wellId, Integer flmId, @Valid FluidLevelMeasurementRequest fluidLevelMeasurementRequest) {
        flmService.updateSpecificFLMS(wellId, flmId, fluidLevelMeasurementRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> wellsWellIdFluidLevelMeasurementsFlmIdDelete(Integer wellId, Integer flmId) {
        flmService.deleteSpecificFLMS(wellId, flmId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    ######################GeneralProductionInfo#########################

    //get all in gen
    @Override
    public ResponseEntity<List<AllProductionGeneralInfoWithNamesResponse>> wellsProductionGeneralInfoGet() {
        return ResponseEntity.ok(pgiService.getAllPGIS());
    }

    @Override
    public ResponseEntity<List<ProductionGeneralInfoResponse>> wellsWellIdProductionGeneralInfoGet(Integer wellId, @Valid String powerSourceType, @Valid String processionPlant, @Valid String currentWellType, @Valid String currentLiftType, @Valid String currentStatus) {
        //todo ask basiony can they be together or req of each alone?
        if(powerSourceType != null){
            return ResponseEntity.ok(pgiService.getAllPGISForAWellPowerSourceType(wellId, powerSourceType));
        } else if (processionPlant != null){
            return ResponseEntity.ok(pgiService.getAllPGISForAWellProcessionPlant(wellId, processionPlant));
        } else if (currentWellType != null){
            return ResponseEntity.ok(pgiService.getAllPGISForAWellCurrentWellType(wellId, currentWellType));
        } else if (currentLiftType != null){
            return ResponseEntity.ok(pgiService.getAllPGISForAWellCurrentLiftType(wellId, currentLiftType));
        } else if (currentStatus != null){
            return ResponseEntity.ok(pgiService.getAllPGISForAWellCurrentStatus(wellId, currentStatus));
        } else {
            return ResponseEntity.ok(pgiService.getAllPGISForAWell(wellId));
        }
    }

    @Override
    public ResponseEntity<Void> wellsWellIdProductionGeneralInfoPost(Integer wellId, @Valid ProductionGeneralInfoRequest productionGeneralInfoRequest) {
        pgiService.insert(productionGeneralInfoRequest, wellId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> wellsWellIdProductionGeneralInfoPgiIdPut(Integer wellId, Integer pgiId, @Valid ProductionGeneralInfoRequest productionGeneralInfoRequest) {
        pgiService.updateSpecificPGIS(wellId, pgiId, productionGeneralInfoRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> wellsWellIdProductionGeneralInfoPgiIdDelete(Integer wellId, Integer pgiId) {
        pgiService.deleteSpecificPGIS(wellId, pgiId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    ######################DrillingInfo#########################

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoDelete(Integer wellId) {
        drillingInfoService.delete(wellId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<DrillingInfoDataResponse>> wellsWellIdDrillingInfoGet(Integer wellId) {
        List<DrillingInfoDataResponse> drillingInfoDataResponses = drillingInfoService.getForWellId(wellId);
        if (drillingInfoDataResponses == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok(drillingInfoDataResponses);
        }
    }

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoIdDelete(Integer wellId, Integer id) {
        drillingInfoService.deleteWellInSpecificId(wellId , id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<DrillingInfoDataResponse> wellsWellIdDrillingInfoIdGet(Integer wellId, Integer id) {
        DrillingInfoDataResponse drillingInfoDataResponse =drillingInfoService.getWellForId(wellId , id);
        if(drillingInfoDataResponse == null)
        {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(drillingInfoDataResponse);
        }
    }

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoIdPatch(Integer wellId, Integer id, DrillingInfoDataRequest drillingInfoDataRequest) {
        drillingInfoService.updateWellForId(wellId ,id ,drillingInfoDataRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoPost(Integer wellId, DrillingInfoDataRequest drillingInfoDataRequest) {
        drillingInfoService.creat(drillingInfoDataRequest, wellId);
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<List<DrillingInfoDataResponse>> wellsDrillingInfoGet() {
        List<DrillingInfoDataResponse> drillingInfoDataResponses = new ArrayList<>();
        drillingInfoDataResponses = drillingInfoService.getAllDrillingInfo();
        if (drillingInfoDataResponses == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok(drillingInfoDataResponses);
        }

    }
}
