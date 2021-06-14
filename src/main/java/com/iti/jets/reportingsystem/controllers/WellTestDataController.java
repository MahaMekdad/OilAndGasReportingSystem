package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.WellsApi;
import com.iti.jets.openapi.model.WellTestRequest;
import com.iti.jets.openapi.model.WellTestResponse;
import com.iti.jets.reportingsystem.services.WellTestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class WellTestDataController implements WellsApi {
    private final WellTestDataService wellTestDataService;

    @Autowired
    public WellTestDataController(WellTestDataService wellTestDataService) {
        this.wellTestDataService = wellTestDataService;
    }

    @Override
    public ResponseEntity<List<WellTestResponse>> findAllTests() {
        List<WellTestResponse> responseList = wellTestDataService.getAllTestsForWells();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<WellTestResponse>> getTestById(Integer wellId, OffsetDateTime beginDate, OffsetDateTime endDate) {
        List<WellTestResponse> responsesList = wellTestDataService.getAllTestsForAWell(wellId);
        return ResponseEntity.ok(responsesList);
    }

    @Override
    public ResponseEntity<WellTestResponse> addTestRecord(Integer id, WellTestRequest wellTestRequest) {
        WellTestResponse wellTestResponse = wellTestDataService.insert(id,wellTestRequest);
        return new ResponseEntity<>(wellTestResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<WellTestResponse> updateWellTestRecord(Integer id, Integer recordId, WellTestRequest wellTestRequest) {
        WellTestResponse
                wellTestResponse = wellTestDataService.updateSpecificTest(id, recordId, wellTestRequest);
        return new ResponseEntity<>(wellTestResponse, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteTest(Integer recordId, Integer id) {
        wellTestDataService.deleteTestRecordByWellIdAndRecordId(id, recordId);
        return ResponseEntity.noContent().build();
    }

}
