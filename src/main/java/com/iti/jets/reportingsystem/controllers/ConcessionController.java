package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.ConcessionsApi;
import com.iti.jets.openapi.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

public class ConcessionController implements ConcessionsApi {

    @Override
    public ResponseEntity<FieldsBudgetAndActualResponse> addBudgetRecord(@Valid FieldsBudgetAndActualRequest fieldsBudgetAndActualRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ConcessionResponse> addConcession(@Valid ConcessionRequest concessionRequest) {
        return null;
    }

    @Override
    public ResponseEntity<List<ProductionBudegetDataResponse>> concessionsBudgetProductionBudgetGet(@Valid OffsetDateTime date) {
        return null;
    }

    @Override
    public ResponseEntity<Void> concessionsBudgetProductionBudgetIdDelete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> concessionsBudgetProductionBudgetIdPatch(Integer id, @Valid List<ProductionBudegetRequest> productionBudegetRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> concessionsBudgetProductionBudgetPost(@Valid List<ProductionBudegetRequest> productionBudegetRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteConcession(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<ConcessionResponse>> findAllConcessions() {
        return null;
    }

    @Override
    public ResponseEntity<List<FieldsBudgetAndActualResponse>> findAllRecords() {
        return null;
    }

    @Override
    public ResponseEntity<ConcessionResponse> getConcessionById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<FieldsBudgetAndActualResponse> updateBudgetRecord(Long id, @Valid FieldsBudgetAndActualRequest fieldsBudgetAndActualRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ConcessionResponse> updateConcession(Long id, @Valid ConcessionRequest concessionRequest) {
        return null;
    }
}
