package com.iti.jets.reportingsystem.services;

import com.iti.jets.reportingsystem.entities.ProductionBudget;
import com.iti.jets.reportingsystem.models.ProductionBudgetModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface ProductionBudgetService {
    List<ProductionBudgetModel> getGetAllProductionBudget();
    void create (ProductionBudgetModel productionBudgetModel);
    void delete (int id);
    ProductionBudgetModel findProductionBudgetByProductionDate(String date);
    ProductionBudgetModel findProductionBudgetById(int id);

}
