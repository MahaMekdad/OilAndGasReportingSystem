package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.ConcessionsApi;
import com.iti.jets.openapi.model.ConcessionRequest;
import com.iti.jets.openapi.model.ConcessionResponse;
import com.iti.jets.openapi.model.FieldsBudgetAndActualRequest;
import com.iti.jets.openapi.model.FieldsBudgetAndActualResponse;
import com.iti.jets.reportingsystem.services.BudgetVsActualService;
import com.iti.jets.reportingsystem.services.ConcessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

//@RestController
public class ConcessionController implements ConcessionsApi {

    private final ConcessionService concessionService;
    private final BudgetVsActualService budgetVsActualService;

    @Autowired
    public ConcessionController(ConcessionService concessionService, BudgetVsActualService budgetVsActualService) {
        this.concessionService = concessionService;
        this.budgetVsActualService = budgetVsActualService;
    }


    @Override
    public ResponseEntity<ConcessionResponse> addConcession(@Valid ConcessionRequest concessionRequest) {
        ConcessionResponse concessionResponse = concessionService.addConcession(concessionRequest);
        return new ResponseEntity<>(concessionResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ConcessionResponse>> findAllConcessions() {
        List<ConcessionResponse> concessions = concessionService.findAllConcessions();
        return new ResponseEntity<>(concessions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ConcessionResponse> getConcessionById(Integer id) {
        ConcessionResponse concessionResponse = null;
        try {
            concessionResponse = concessionService.findConcessionById(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(concessionResponse, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ConcessionResponse> updateConcession(Integer id, @Valid ConcessionRequest concessionRequest) {
        ConcessionResponse concessionResponse = null;
        try {
            concessionResponse = concessionService.updateConcession(id, concessionRequest);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(concessionResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteConcession(Integer id) {
        concessionService.deleteConcession(Math.toIntExact(id));
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FieldsBudgetAndActualResponse>> findAllRecords() {
        return ResponseEntity.ok(budgetVsActualService.findAll());
    }

    @Override
    public ResponseEntity<FieldsBudgetAndActualResponse> updateBudgetRecord(
            Integer id, @Valid FieldsBudgetAndActualRequest fieldsBudgetAndActualRequest) {
        return ResponseEntity.ok(budgetVsActualService.updateBudgetRecord(id, fieldsBudgetAndActualRequest));
    }
    @Override
    public ResponseEntity<Void> concessionsBudgetProductionBudgetIdDelete(Integer id) {
        budgetVsActualService.deleteRecord(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<FieldsBudgetAndActualResponse> addBudgetRecord(
            @Valid FieldsBudgetAndActualRequest fieldsBudgetAndActualRequest) {
        return ResponseEntity.ok(budgetVsActualService.addBudgetRecord(fieldsBudgetAndActualRequest));
    }

}
