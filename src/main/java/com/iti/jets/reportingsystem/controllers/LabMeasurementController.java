package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.WellsApi;
import com.iti.jets.openapi.model.LabMeasurementRequest;
import com.iti.jets.openapi.model.LabMeasurementResponse;
import com.iti.jets.reportingsystem.services.LabMeasurementService;
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
public class LabMeasurementController implements WellsApi {

    private LabMeasurementService labMeasurementService;

    @Autowired
    public LabMeasurementController(LabMeasurementService labMeasurementService) {
        this.labMeasurementService = labMeasurementService;
    }

    @Override
    public ResponseEntity<List<LabMeasurementResponse>> getAllLabs(@Valid String beginDate, @Valid String endDate) {
        if (beginDate != null && endDate != null) {
            try {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(beginDate);
                Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
                return ResponseEntity.ok(labMeasurementService.getAllLabs(date1, date2));
            } catch (ParseException p) {
            }
        } else {
            return ResponseEntity.ok(labMeasurementService.getAllLabs());
        }
        return null;
    }


    @Override
    public ResponseEntity<List<LabMeasurementResponse>> getAllLabsInWell(Long wellId, @Valid String beginDate, @Valid String endDate) {
        if (beginDate != null && endDate != null) {
            try {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(beginDate);
                Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
                return ResponseEntity.ok(labMeasurementService.getAllLabsFromWell(Math.toIntExact(wellId), date1, date2));
            } catch (ParseException p) {
            }
        } else {
            return ResponseEntity.ok(labMeasurementService.getAllLabsFromWell(Math.toIntExact(wellId)));
        }
        return null;
    }

    @Override
    public ResponseEntity<LabMeasurementResponse> getLabByWellIdAndLabId(Long wellId, Long labId) {

        return ResponseEntity.ok(labMeasurementService.getAlabFromAwell(Math.toIntExact(wellId), Math.toIntExact(labId)));
    }

    @Override
    public ResponseEntity<Void> deleteLabById(Integer wellId, Integer labId) {
        labMeasurementService.delete(wellId, labId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateLabMeasurement(Long wellId, Long labId, @Valid LabMeasurementRequest labMeasurementRequest) {
        labMeasurementService.update(Math.toIntExact(wellId), Math.toIntExact(labId), labMeasurementRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addLabMeasurement(Long wellId, @Valid LabMeasurementRequest labMeasurementRequest) {
        labMeasurementService.insert(Math.toIntExact(wellId), labMeasurementRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}





