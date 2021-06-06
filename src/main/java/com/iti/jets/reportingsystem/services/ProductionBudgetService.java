package com.iti.jets.reportingsystem.services;

import com.iti.jets.reportingsystem.models.ProductionBudgetModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductionBudgetService {
    List<ProductionBudgetModel> getGetAllProductionBudget();

}
