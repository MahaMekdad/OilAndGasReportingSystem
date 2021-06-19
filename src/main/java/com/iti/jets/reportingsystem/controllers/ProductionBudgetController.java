package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.ConcessionsApi;
import com.iti.jets.openapi.model.*;
import com.iti.jets.reportingsystem.services.BudgetVsActualService;
import com.iti.jets.reportingsystem.services.ConcessionService;
import com.iti.jets.reportingsystem.services.ProductionBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class ProductionBudgetController implements ConcessionsApi {
    @Autowired
    private ProductionBudgetService productionBudgetService;
    private final ConcessionService concessionService;
    private final BudgetVsActualService budgetVsActualService;

    @Autowired
    public ProductionBudgetController(ConcessionService concessionService, BudgetVsActualService budgetVsActualService) {
        this.concessionService = concessionService;
        this.budgetVsActualService = budgetVsActualService;
    }

    @CrossOrigin
    @Override
    public ResponseEntity<List<ProductionBudegetDataResponse>> concessionsBudgetProductionBudgetGet(OffsetDateTime date) {
        if (date != null) {
            System.out.println("inside if date != null");
            List<ProductionBudegetDataResponse> productionBudgetModel = new ArrayList<>();
            LocalDate localDate = date.toLocalDate();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            System.out.println(localDate);
            Date date1 = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            productionBudgetModel = productionBudgetService.findProductionBudgetByProductionDate(date1);
            if (productionBudgetModel == null)
                return ResponseEntity.notFound().build();
            else {
                return ResponseEntity.ok(productionBudgetModel);
            }
        } else {
            System.out.println("inside else");
            List<ProductionBudegetDataResponse> productionBudgetModels = new ArrayList<>();
            productionBudgetModels = productionBudgetService.getGetAllProductionBudget();
//            for(int i =0; i<productionBudgetModels.size() ; i++)
//            {
//
//                LocalDate localDate = productionBudgetModels.get(i).getProductionDate().toLocalDate();
//                ZoneId defaultZoneId = ZoneId.systemDefault();
//                System.out.println(localDate);
//                Date date1 = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
//                productionBudgetModels.get(i).setProductionDate(date1);
//            }
            System.out.println("dateee== " + productionBudgetModels.get(0).getProductionDate());
            if (productionBudgetModels == null)
                return ResponseEntity.notFound().build();
            else {
                return ResponseEntity.ok(productionBudgetModels);
            }
        }
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER')")
    //Salma's table Production Budget
    @CrossOrigin
    @Override
    public ResponseEntity<Void> concessionsBudgetProductionBudgetIdDelete(Integer id) {
        productionBudgetService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER')")
    @Override
    public ResponseEntity<Void> concessionsBudgetProductionBudgetIdPatch(Integer id, ProductionBudegetRequest productionBudegetRequest) {
        productionBudgetService.updateProductionBudget(id, productionBudegetRequest);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER')")
    @Override
    public ResponseEntity<Void> concessionsBudgetProductionBudgetPost(ProductionBudegetRequest productionBudegetRequest) {
        productionBudgetService.create(productionBudegetRequest);
        return ResponseEntity.ok().build();
    }


    //---------------------Basiony, adding concession functionality--------------
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
    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isConcessionMember(#id))")
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


    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isConcessionMember(#id))")
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

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isConcessionMember(#id))")
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
    public ResponseEntity<Void> deleteRecord(Integer id) {
//        deleting budget vs actual record
        budgetVsActualService.deleteRecord(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<FieldsBudgetAndActualResponse> addBudgetRecord(
            @Valid FieldsBudgetAndActualRequest fieldsBudgetAndActualRequest) {
        return ResponseEntity.ok(budgetVsActualService.addBudgetRecord(fieldsBudgetAndActualRequest));
    }

}
