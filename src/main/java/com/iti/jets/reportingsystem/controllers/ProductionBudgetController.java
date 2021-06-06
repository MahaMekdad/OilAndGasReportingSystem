package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.entities.ProductionBudget;
import com.iti.jets.reportingsystem.models.ProductionBudgetModel;
import com.iti.jets.reportingsystem.repos.ProductionBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/concessions/budget")
public class ProductionBudgetController {
    @Autowired
    private ProductionBudgetRepository productionBudgetRepository;

    @GetMapping("/productionBudget")
    public List<ProductionBudgetModel> getAllProductionBudget()
    {
        List<ProductionBudgetModel> productionBudgetModels = new ArrayList<>();
        productionBudgetModels = productionBudgetRepository.findAll();
        return productionBudgetModels;

    }

}
