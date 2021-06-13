package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.WellsApi;

import com.iti.jets.openapi.model.WellDailyActionsRequest;
import com.iti.jets.openapi.model.WellDailyActionsResponse;
import com.iti.jets.reportingsystem.entities.DailyActions;
import com.iti.jets.reportingsystem.services.DailyActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//@RestController
public class DailyActionsController implements WellsApi {

    private DailyActionsService dailyActionsService;

    @Autowired
    public DailyActionsController(DailyActionsService dailyActionsService) {
        this.dailyActionsService = dailyActionsService;
    }

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


//    @Override
//    public ResponseEntity<List<LabMeasurementResponse>> getAllLabsInWell(Long wellId , @Valid String beginDate, @Valid String endDate) {
//        if (beginDate != null && endDate != null) {
//            try {
//                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(beginDate);
//                Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
//                return ResponseEntity.ok(labMeasurementService.getAllLabsFromWell(Math.toIntExact(wellId),date1,date2));
//            } catch (ParseException p) {
//            }
//        }else{
//            return ResponseEntity.ok(labMeasurementService.getAllLabsFromWell(Math.toIntExact(wellId)));
//        }
//        return null;
//    }
//
    @Override
    public ResponseEntity<WellDailyActionsResponse> getWellReportById(Long wellId , Long reportId) {

        return ResponseEntity.ok(dailyActionsService.getAdailyActionFromAwell(Math.toIntExact(wellId),Math.toIntExact(reportId)));
    }

    @Override
    public ResponseEntity<Void> deleteReportById(Integer wellId, Integer reportId) {
        dailyActionsService.delete(wellId, reportId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateWellReport(Long wellId, Long reportId, @Valid WellDailyActionsRequest wellDailyActionsRequest) {
        dailyActionsService.update(Math.toIntExact(wellId), Math.toIntExact(reportId), wellDailyActionsRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//
    @Override
    public ResponseEntity<Void> addDailyReport(Long wellId, @Valid WellDailyActionsRequest wellDailyActionsRequest) {
        dailyActionsService.insert( Math.toIntExact(wellId),wellDailyActionsRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//
//}





}
