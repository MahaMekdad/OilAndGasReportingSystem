package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.WellsApi;
import com.iti.jets.openapi.model.*;
import com.iti.jets.reportingsystem.entities.IntervalsInfo;
import com.iti.jets.reportingsystem.entities.WellGeneralInfo;
import com.iti.jets.reportingsystem.services.*;
import com.iti.jets.reportingsystem.services.impls.IntervalsInfoServiceImpl;
import com.iti.jets.reportingsystem.services.impls.WellGeneralInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class FluidLevelMeasurementsController implements WellsApi {


    private final FluidLevelMeasurementsService flmService;
    private final ProductionGeneralInfoService pgiService;
    private final DrillingInfoService drillingInfoService;
    private final WellGeneralInfoService wellGeneralInfoService;
    private final IntervalsInfoService intervalsInfoService;

    @Autowired
    public FluidLevelMeasurementsController(FluidLevelMeasurementsService flmService, ProductionGeneralInfoService pgiService, DrillingInfoService drillingInfoService
    , WellGeneralInfoServiceImpl wellGeneralInfoService, IntervalsInfoServiceImpl intervalsInfoService){
        this.flmService = flmService;
        this.pgiService = pgiService;
        this.drillingInfoService = drillingInfoService;
        this.intervalsInfoService=intervalsInfoService;
        this.wellGeneralInfoService=wellGeneralInfoService;
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

    //

    @Override
    public ResponseEntity<List<WellGeneralInfoResponse>> wellsGeneralInfoGet() {
        return ResponseEntity.ok(wellGeneralInfoService.getAllWellsGeneralInfo());
    }

    @Override
    public ResponseEntity<Void> wellsGeneralInfoIdDelete(Integer id) {
        if(wellGeneralInfoService.deleteWellGeneralInfo(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<WellGeneralInfoResponse> wellsGeneralInfoIdGet(Integer id) {
        WellGeneralInfoResponse wellGeneralInfoResponse=wellGeneralInfoService.getWellGeneralInfoById(id);
        if (wellGeneralInfoResponse==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(wellGeneralInfoResponse);
    }

    @Override
    public ResponseEntity<Void> wellsGeneralInfoIdPut(Integer id, @Valid WellGeneralInfoRequest wellGeneralInfoRequest) {
        if(wellGeneralInfoService.updateWellGeneralInfo(id,wellGeneralInfoRequest)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> wellsGeneralInfoPost(@Valid WellGeneralInfoRequest wellGeneralInfoRequest) {
        WellGeneralInfo wellGeneralInfo=wellGeneralInfoService.saveWellGeneralInfo(wellGeneralInfoRequest);
        if(wellGeneralInfo==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    //Intervals Information

    @Override
    public ResponseEntity<List<IntervalsInfoResponse>> wellsIntervalsInfoGet() {
        return ResponseEntity.ok(intervalsInfoService.getAllIntervalsInfo());
    }

    @Override
    public ResponseEntity<Void> wellsIntervalsInfoIdDelete(Integer id) {
        if(intervalsInfoService.deleteIntervalsInfo(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<IntervalsInfoResponse> wellsIntervalsInfoIdGet(Integer id) {
        IntervalsInfoResponse intervalsInfoResponse=intervalsInfoService.getIntervalsInfoById(id);
        if (intervalsInfoResponse==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(intervalsInfoResponse);
    }

    @Override
    public ResponseEntity<Void> wellsIntervalsInfoIdPut(Integer id, @Valid IntervalsInfoRequest intervalsInfoRequest) {
        if(intervalsInfoService.updateIntervalsInfo(id,intervalsInfoRequest)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> wellsIntervalsInfoPost(@Valid IntervalsInfoRequest intervalsInfoRequest) {
        IntervalsInfo intervalsInfo=intervalsInfoService.saveIntervalsInfo(intervalsInfoRequest);
        if(intervalsInfo==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
