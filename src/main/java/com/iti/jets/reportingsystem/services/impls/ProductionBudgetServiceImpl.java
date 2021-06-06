package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.models.ProductionBudgetModel;
import com.iti.jets.reportingsystem.repos.ProductionBudgetRepository;
import com.iti.jets.reportingsystem.services.ProductionBudgetService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductionBudgetServiceImpl implements ProductionBudgetService {
    @Autowired
    private ProductionBudgetRepository productionBudgetRepository;

    @Override
    public List<ProductionBudgetModel> getGetAllProductionBudget() {
        List<ProductionBudgetModel> productionBudgetModels = new ArrayList<>();
        productionBudgetModels = productionBudgetRepository.findAll();
        return productionBudgetModels;

    }
}
