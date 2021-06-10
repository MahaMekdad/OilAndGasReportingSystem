package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.ProductionBudegetDataResponse;
import com.iti.jets.reportingsystem.entities.ProductionBudget;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface ProductionBudgetService {
    List<ProductionBudegetDataResponse> getGetAllProductionBudget();
    void create (ProductionBudegetDataResponse productionBudgetModel);
    void delete (int id);
    ProductionBudegetDataResponse findProductionBudgetByProductionDate(Date date);
    ProductionBudegetDataResponse findProductionBudgetById(int id);
    void updateProductionBudget(int id,ProductionBudegetDataResponse productionBudgetModel);

}
