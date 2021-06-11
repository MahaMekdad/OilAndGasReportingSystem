package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.ConcessionsApi;
import com.iti.jets.openapi.model.ProductionBudegetDataResponse;
import com.iti.jets.openapi.model.ProductionBudegetRequest;
import com.iti.jets.reportingsystem.services.ProductionBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
public class ProductionBudgetController implements com.iti.jets.openapi.api.ConcessionsApi {
    @Autowired
    private ProductionBudgetService productionBudgetService;

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
            System.out.println("dateee== "+productionBudgetModels.get(0).getProductionDate());
            if (productionBudgetModels == null)
                return ResponseEntity.notFound().build();
            else {
                return ResponseEntity.ok(productionBudgetModels);
            }
        }
    }

    @Override
    public ResponseEntity<Void> concessionsBudgetProductionBudgetIdDelete(Integer id) {
        productionBudgetService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> concessionsBudgetProductionBudgetIdPatch(Integer id, ProductionBudegetRequest productionBudegetRequest) {
        productionBudgetService.updateProductionBudget(id, productionBudegetRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> concessionsBudgetProductionBudgetPost(ProductionBudegetRequest productionBudegetRequest) {
        productionBudgetService.create(productionBudegetRequest);
        return ResponseEntity.ok().build();
    }

}
