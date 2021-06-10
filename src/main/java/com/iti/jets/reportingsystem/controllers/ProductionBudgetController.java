package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.ConcessionsApi;
import com.iti.jets.openapi.model.ProductionBudegetDataResponse;
import com.iti.jets.openapi.model.ProductionBudegetRequest;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.List;

public class ProductionBudgetController implements com.iti.jets.openapi.api.ConcessionsApi {
    @Override
    public ResponseEntity<Void> concessionsBudgetProductionBudgetIdDelete(Integer id) {
        return ConcessionsApi.super.concessionsBudgetProductionBudgetIdDelete(id);
    }

    @Override
    public ResponseEntity<Void> concessionsBudgetProductionBudgetPost(List<ProductionBudegetRequest> productionBudegetRequest) {
        return ConcessionsApi.super.concessionsBudgetProductionBudgetPost(productionBudegetRequest);
    }

    @Override
    public ResponseEntity<List<ProductionBudegetDataResponse>> concessionsBudgetProductionBudgetGet(OffsetDateTime date) {
        return ConcessionsApi.super.concessionsBudgetProductionBudgetGet(date);
    }

    @Override
    public ResponseEntity<Void> concessionsBudgetProductionBudgetIdPatch(Integer id, List<ProductionBudegetRequest> productionBudegetRequest) {
        return ConcessionsApi.super.concessionsBudgetProductionBudgetIdPatch(id, productionBudegetRequest);
    }
}
