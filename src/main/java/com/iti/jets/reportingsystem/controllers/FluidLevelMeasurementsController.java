package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.WellsApi;
import com.iti.jets.openapi.model.*;
import com.iti.jets.reportingsystem.entities.Field;
import com.iti.jets.reportingsystem.entities.IntervalsInfo;
import com.iti.jets.reportingsystem.entities.WellGeneralInfo;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.models.WellCoordinatesDto;
import com.iti.jets.reportingsystem.services.*;
import com.iti.jets.reportingsystem.services.impls.IntervalsInfoServiceImpl;
import com.iti.jets.reportingsystem.services.impls.WellGeneralInfoServiceImpl;
import com.iti.jets.reportingsystem.utils.helpers.RepoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
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
    private final WellGeneralInfoService wellGeneralInfoService;
    private final IntervalsInfoService intervalsInfoService;
    private final WellService wellService;
    private final WellTestDataService wellTestDataService;
    private final LabMeasurementService labMeasurementService;
    private final DailyActionsService dailyActionsService;
    private final RepoHelper repoHelper;

    @Autowired
    public FluidLevelMeasurementsController(FluidLevelMeasurementsService flmService, ProductionGeneralInfoService pgiService, DrillingInfoService drillingInfoService
            , WellGeneralInfoServiceImpl wellGeneralInfoService, IntervalsInfoServiceImpl intervalsInfoService,
                                            WellService wellService, WellTestDataService wellTestDataService, LabMeasurementService labMeasurementService, DailyActionsService dailyActionsService, RepoHelper repoHelper) {
        this.flmService = flmService;
        this.pgiService = pgiService;
        this.drillingInfoService = drillingInfoService;
        this.intervalsInfoService = intervalsInfoService;
        this.wellGeneralInfoService = wellGeneralInfoService;
        this.wellService = wellService;
        this.wellTestDataService = wellTestDataService;
        this.labMeasurementService = labMeasurementService;
        this.dailyActionsService = dailyActionsService;
        this.repoHelper = repoHelper;
    }

//    ######################FluidLevelMeasurements#########################

    //get all in gen
    @PreAuthorize("permitAll()")
    @Override
    public ResponseEntity<List<AllFluidLevelMeasurementResponse>> wellsFluidLevelMeasurementsGet(@Valid OffsetDateTime beginDate, @Valid OffsetDateTime endDate) {
        if (beginDate != null && endDate != null) {
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
//    @PreAuthorize("isFlmConcessionMember(#wellId)")
//    @PreAuthorize("repoHelper.isFlmConcessionMember(#wellId)")
//    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId, principal.username))")
    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
//    @PreAuthorize("@mySecurityService.isFlmConcessionMember(#wellId)")
    @Override
    public ResponseEntity<List<FluidLevelMeasurementResponse>> wellsWellIdFluidLevelMeasurementsGet(Integer wellId, @Valid OffsetDateTime beginDate, @Valid OffsetDateTime endDate) {
        if (beginDate != null && endDate != null) {
            Date begin = Date.from(beginDate.toInstant());
            System.out.println(begin + " <== begin");
            Date end = Date.from(endDate.toInstant());
            System.out.println(end + " <== end");
            return ResponseEntity.ok(flmService.getAllFLMSForAWell(wellId, begin, end));
        } else {
            return ResponseEntity.ok(flmService.getAllFLMSForAWell(wellId));
        }
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<Void> wellsWellIdFluidLevelMeasurementsPost(Integer wellId, @Valid FluidLevelMeasurementRequest fluidLevelMeasurementRequest) {
        flmService.insert(fluidLevelMeasurementRequest, wellId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<Void> wellsWellIdFluidLevelMeasurementsFlmIdPatch(Integer wellId, Integer flmId, @Valid FluidLevelMeasurementRequest fluidLevelMeasurementRequest) {
        flmService.updateSpecificFLMS(wellId, flmId, fluidLevelMeasurementRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<Void> wellsWellIdFluidLevelMeasurementsFlmIdPut(Integer wellId, Integer flmId, @Valid FluidLevelMeasurementRequest fluidLevelMeasurementRequest) {
        flmService.updateSpecificFLMS(wellId, flmId, fluidLevelMeasurementRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
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

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<List<ProductionGeneralInfoResponse>> wellsWellIdProductionGeneralInfoGet(Integer wellId, @Valid String powerSourceType, @Valid String processionPlant, @Valid String currentWellType, @Valid String currentLiftType, @Valid String currentStatus) {
        //todo ask basiony can they be together or req of each alone?
        if (powerSourceType != null) {
            return ResponseEntity.ok(pgiService.getAllPGISForAWellPowerSourceType(wellId, powerSourceType));
        } else if (processionPlant != null) {
            return ResponseEntity.ok(pgiService.getAllPGISForAWellProcessionPlant(wellId, processionPlant));
        } else if (currentWellType != null) {
            return ResponseEntity.ok(pgiService.getAllPGISForAWellCurrentWellType(wellId, currentWellType));
        } else if (currentLiftType != null) {
            return ResponseEntity.ok(pgiService.getAllPGISForAWellCurrentLiftType(wellId, currentLiftType));
        } else if (currentStatus != null) {
            return ResponseEntity.ok(pgiService.getAllPGISForAWellCurrentStatus(wellId, currentStatus));
        } else {
            return ResponseEntity.ok(pgiService.getAllPGISForAWell(wellId));
        }
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<Void> wellsWellIdProductionGeneralInfoPost(Integer wellId, @Valid ProductionGeneralInfoRequest productionGeneralInfoRequest) {
        pgiService.insert(productionGeneralInfoRequest, wellId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<Void> wellsWellIdProductionGeneralInfoPgiIdPut(Integer wellId, Integer pgiId, @Valid ProductionGeneralInfoRequest productionGeneralInfoRequest) {
        pgiService.updateSpecificPGIS(wellId, pgiId, productionGeneralInfoRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<Void> wellsWellIdProductionGeneralInfoPgiIdDelete(Integer wellId, Integer pgiId) {
        pgiService.deleteSpecificPGIS(wellId, pgiId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    ######################DrillingInfo#########################

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoDelete(Integer wellId) {

        drillingInfoService.delete(wellId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<List<DrillingInfoDataResponse>> wellsWellIdDrillingInfoGet(Integer wellId) {
        List<DrillingInfoDataResponse> drillingInfoDataResponses = drillingInfoService.getForWellId(wellId);
            return ResponseEntity.ok(drillingInfoDataResponses);

    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoIdDelete(Integer wellId, Integer id) {
        drillingInfoService.deleteWellInSpecificId(wellId, id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<DrillingInfoDataResponse> wellsWellIdDrillingInfoIdGet(Integer wellId, Integer id) {
        DrillingInfoDataResponse drillingInfoDataResponse = drillingInfoService.getWellForId(wellId, id);
            return ResponseEntity.ok(drillingInfoDataResponse);

    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoIdPatch(Integer wellId, Integer id, DrillingInfoDataRequest drillingInfoDataRequest) {
        drillingInfoService.updateWellForId(wellId, id, drillingInfoDataRequest);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoPost(Integer wellId, DrillingInfoDataRequest drillingInfoDataRequest) {
        drillingInfoService.creat(drillingInfoDataRequest, wellId);
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<List<DrillingInfoDataResponse>> wellsDrillingInfoGet() {
        List<DrillingInfoDataResponse> drillingInfoDataResponses = new ArrayList<>();
        drillingInfoDataResponses = drillingInfoService.getAllDrillingInfo();

            return ResponseEntity.ok(drillingInfoDataResponses);


    }

//    ######################wellGeneralInfo#########################

    @Override
    public ResponseEntity<List<WellGeneralInfoResponse>> wellsGeneralInfoGet() {
        return ResponseEntity.ok(wellGeneralInfoService.getAllWellsGeneralInfo());
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isWellGenInfoConcessionMember(#id))")
    @Override
    public ResponseEntity<Void> wellsGeneralInfoIdDelete(Integer id) {
        if (wellGeneralInfoService.deleteWellGeneralInfo(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isWellGenInfoConcessionMember(#id))")
    @Override
    public ResponseEntity<WellGeneralInfoResponse> wellsGeneralInfoIdGet(Integer id) {
        WellGeneralInfoResponse wellGeneralInfoResponse = wellGeneralInfoService.getWellGeneralInfoById(id);
        if (wellGeneralInfoResponse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(wellGeneralInfoResponse);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isWellGenInfoConcessionMember(#id))")
    @Override
    public ResponseEntity<Void> wellsGeneralInfoIdPut(Integer id, @Valid WellGeneralInfoRequest wellGeneralInfoRequest) {
        if (wellGeneralInfoService.updateWellGeneralInfo(id, wellGeneralInfoRequest)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isWellGenInfoConcessionMember(#wellGeneralInfoRequest.wellId))")
    @Override
    public ResponseEntity<Void> wellsGeneralInfoPost(@Valid WellGeneralInfoRequest wellGeneralInfoRequest) {

        WellGeneralInfo wellGeneralInfo = wellGeneralInfoService.saveWellGeneralInfo(wellGeneralInfoRequest);
        if (wellGeneralInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isWellGenInfoConcessionMember(#wellGeneralInfoRequest.wellId))")
    @GetMapping("/wellsCoordinates/{fieldId}")
    public ResponseEntity<List<WellCoordinatesDto>> getWellsCoordinates(@PathVariable Integer fieldId) {
        List<WellGeneralInfo> wellGeneralInfos=wellGeneralInfoService.getWellsCoordinates(fieldId);
        List<WellCoordinatesDto> wellCoordinatesDtoList=new ArrayList<>();
        for(WellGeneralInfo wellGeneralInfo:wellGeneralInfos){
            wellCoordinatesDtoList.add(new WellCoordinatesDto(wellGeneralInfo.getXcord(),wellGeneralInfo.getYcord(),wellGeneralInfo.getWell().getWellName()));
        }
        return ResponseEntity.ok(wellCoordinatesDtoList);
    }
//    ######################IntervalsInformation#########################


    @Override
    public ResponseEntity<List<IntervalsInfoResponse>> wellsIntervalsInfoGet() {
        return ResponseEntity.ok(intervalsInfoService.getAllIntervalsInfo());
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isIntervalsInfoConcessionMember(#id))")
    @Override
    public ResponseEntity<Void> wellsIntervalsInfoIdDelete(Integer id) {
        if (intervalsInfoService.deleteIntervalsInfo(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isIntervalsInfoConcessionMember(#id))")
    @Override

    public ResponseEntity<List<IntervalsInfoResponse>> wellsIntervalsInfoIdGet(Integer wellId) {
        List<IntervalsInfoResponse> intervalsInfoResponse=intervalsInfoService.getIntervalsInfoById(wellId);
        if (intervalsInfoResponse==null){

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(intervalsInfoResponse);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isIntervalsInfoConcessionMember(#id))")
    @Override
    public ResponseEntity<Void> wellsIntervalsInfoIdPut(Integer id, @Valid IntervalsInfoRequest intervalsInfoRequest) {
        if (intervalsInfoService.updateIntervalsInfo(id, intervalsInfoRequest)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#intervalsInfoRequest.wellId))")
    @Override
    public ResponseEntity<Void> wellsIntervalsInfoPost(@Valid IntervalsInfoRequest intervalsInfoRequest) {
        IntervalsInfo intervalsInfo = intervalsInfoService.saveIntervalsInfo(intervalsInfoRequest);
        if (intervalsInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    ######################Well#########################

    @Override
    public ResponseEntity<List<WellResponse>> getwells() {
        List<WellResponse> responseList = wellService.getAllWells();
        return ResponseEntity.ok(responseList);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId.intValue()))")
    @Override
    public ResponseEntity<WellResponse> getwellById(Long wellId) {
//        if(wellService.getWellByID(Math.toIntExact(wellId)) == null)
//        {
//            System.out.println(">????????");
//            throw  new ResourceNotFoundException("No well found");
//        }
        return ResponseEntity.ok(wellService.getWellByID(Math.toIntExact(wellId)));
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#id))")
    @Override
    public ResponseEntity<Void> deleteWellById(Integer id) {
        wellService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#id.intValue()))")
    @Override
    public ResponseEntity<Void> updateWell(Long id, @Valid WellRequest wellRequest) {

        WellResponse wellResponse = null;
        try {
//            wellResponse = wellService.updateWell(Math.toIntExact(id), wellRequest);
            wellService.updateWell(Math.toIntExact(id), wellRequest);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
//        return new ResponseEntity<>(wellResponse, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isWellConcessionMember(#wellRequest.fieldId()))")
    @Override
    public ResponseEntity<Void> addwell(@Valid WellRequest wellRequest) {
        wellService.insert(wellRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
//        WellResponse wellResponse = wellService.insert(wellRequest);
//        return new ResponseEntity<>(wellResponse, HttpStatus.CREATED);
//        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //    ---------------------Basiony, adding well test manag functionality------------
    @Override
    public ResponseEntity<List<WellTestResponse>> findAllTests() {
        List<WellTestResponse> responseList = wellTestDataService.getAllTestsForWells();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<List<WellTestResponse>> getTestById(Integer wellId, OffsetDateTime beginDate, OffsetDateTime endDate) {
        List<WellTestResponse> responsesList = wellTestDataService.getAllTestsForAWell(wellId);
        return ResponseEntity.ok(responsesList);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#id))")
    @Override
    public ResponseEntity<WellTestResponse> addTestRecord(Integer id, WellTestRequest wellTestRequest) {
        WellTestResponse wellTestResponse = wellTestDataService.insert(id, wellTestRequest);
        return new ResponseEntity<>(wellTestResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#id))")
    @Override
    public ResponseEntity<WellTestResponse> updateWellTestRecord(Integer id, Integer recordId, WellTestRequest wellTestRequest) {
        WellTestResponse
                wellTestResponse = wellTestDataService.updateSpecificTest(id, recordId, wellTestRequest);
        return new ResponseEntity<>(wellTestResponse, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#id))")
    @Override
    public ResponseEntity<Void> deleteTest(Integer recordId, Integer id) {
        wellTestDataService.deleteTestRecordByWellIdAndRecordId(id, recordId);
        return ResponseEntity.noContent().build();
    }

//    ######################LabMeasurements#########################

    @Override
    public ResponseEntity<List<LabMeasurementResponse>> getAllLabs(@Valid String beginDate, @Valid String endDate) {
        if (beginDate != null && endDate != null) {
            try {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(beginDate);
                Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
                return ResponseEntity.ok(labMeasurementService.getAllLabs(date1,date2));
            } catch (ParseException p) {
            }
        }else{
            return ResponseEntity.ok(labMeasurementService.getAllLabs());
        }
        return null;
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId.intValue()))")
    @Override
    public ResponseEntity<List<LabMeasurementResponse>> getAllLabsInWell(Long wellId , @Valid String beginDate, @Valid String endDate) {
        if (beginDate != null && endDate != null) {
            try {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(beginDate);
                Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
                return ResponseEntity.ok(labMeasurementService.getAllLabsFromWell(Math.toIntExact(wellId),date1,date2));
            } catch (ParseException p) {
            }
        }else{
            return ResponseEntity.ok(labMeasurementService.getAllLabsFromWell(Math.toIntExact(wellId)));
        }
        return null;
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId.intValue()))")
    @Override
    public ResponseEntity<LabMeasurementResponse> getLabByWellIdAndLabId(Long wellId , Long labId) {

        return ResponseEntity.ok(labMeasurementService.getAlabFromAwell(Math.toIntExact(wellId),Math.toIntExact(labId)));
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<Void> deleteLabById(Integer wellId, Integer labId) {
        labMeasurementService.delete(wellId, labId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId.intValue()))")
    @Override
    public ResponseEntity<Void> updateLabMeasurement(Long wellId, Long labId, @Valid LabMeasurementRequest labMeasurementRequest) {
        labMeasurementService.update(Math.toIntExact(wellId), Math.toIntExact(labId), labMeasurementRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId.intValue()))")
    @Override
    public ResponseEntity<Void> addLabMeasurement(Long wellId, @Valid LabMeasurementRequest labMeasurementRequest) {
        labMeasurementService.insert( Math.toIntExact(wellId),labMeasurementRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    ######################DailyActions#########################

    @Override
    public ResponseEntity<List<WellDailyActionsResponse>> getAllReports(@Valid Long siLVL4 , @Valid Long losses , @Valid Long downTime , @Valid String beginDate, @Valid String endDate) {
        if (beginDate != null && endDate != null) {
            try {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(beginDate);
                Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
                return ResponseEntity.ok(dailyActionsService.getAllDailyActions(date1,date2));
            } catch (ParseException p) {
            }

        }
        else if(siLVL4!=null){
            return ResponseEntity.ok(dailyActionsService.getAllDailyActionsByShLvl4(siLVL4));
        }
        else if(losses!=null){
            return ResponseEntity.ok(dailyActionsService.getAllDailyActionsByLosses(new Double((losses))));
        }
        else if(downTime!=null){
            return ResponseEntity.ok(dailyActionsService.getAllDailyActionsByDownTime(new Float(downTime)));
        }
        else{
            return ResponseEntity.ok(dailyActionsService.getAllDailyActions());
        }
        return ResponseEntity.ok(dailyActionsService.getAllDailyActions());
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId.intValue()))")
    @Override
    public ResponseEntity<List<WellDailyActionsResponse>> getReportById(Long wellId , @Valid Long siLVL4 , @Valid Long losses , @Valid Long downTime , @Valid String beginDate, @Valid String endDate) {
        if (beginDate != null && endDate != null) {
            try {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(beginDate);
                Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
                return ResponseEntity.ok(dailyActionsService.getAllDailyActionsFromWell(Math.toIntExact(wellId),date1,date2));
            } catch (ParseException p) {
            }

        }
        else if(siLVL4!=null){
            return ResponseEntity.ok(dailyActionsService.getAllDailyActionsFromWellWithShLvl4(Math.toIntExact(wellId),siLVL4));
        }
        else if(losses!=null){
            return ResponseEntity.ok(dailyActionsService.getAllDailyActionsFromWellWithLosses(Math.toIntExact(wellId), Double.valueOf(losses)));
        }
        else if(downTime!=null){
            return ResponseEntity.ok(dailyActionsService.getAllDailyActionsFromWellWithDownTime(Math.toIntExact(wellId), Float.valueOf(downTime)));
        }
        else{
            return ResponseEntity.ok(dailyActionsService.getAllDailyActions());
        }
        return null;
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId.intValue()))")
    @Override
    public ResponseEntity<WellDailyActionsResponse> getWellReportById(Long wellId , Long reportId) {

        return ResponseEntity.ok(dailyActionsService.getAdailyActionFromAwell(Math.toIntExact(wellId),Math.toIntExact(reportId)));
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId))")
    @Override
    public ResponseEntity<Void> deleteReportById(Integer wellId, Integer reportId) {
        dailyActionsService.delete(wellId, reportId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId.intValue()))")
    @Override
    public ResponseEntity<Void> updateWellReport(Long wellId, Long reportId, @Valid WellDailyActionsRequest wellDailyActionsRequest) {
        dailyActionsService.update(Math.toIntExact(wellId), Math.toIntExact(reportId), wellDailyActionsRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //
    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isFlmConcessionMember(#wellId.intValue()))")
    @Override
    public ResponseEntity<Void> addDailyReport(Long wellId, @Valid WellDailyActionsRequest wellDailyActionsRequest) {
        dailyActionsService.insert( Math.toIntExact(wellId),wellDailyActionsRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
