package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.entities.ProductionBudget;
import com.iti.jets.reportingsystem.models.ProductionBudgetModel;
import com.iti.jets.reportingsystem.repos.ProductionBudgetRepository;
import com.iti.jets.reportingsystem.services.ProductionBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/concessions/budget")
public class ProductionBudgetController {
    @Autowired
    private ProductionBudgetService productionBudgetService;

    @GetMapping("/productionBudget")
    public List<ProductionBudgetModel> getAllProductionBudget()
    {
        List<ProductionBudgetModel> productionBudgetModels = new ArrayList<>();
        productionBudgetModels = productionBudgetService.getGetAllProductionBudget();
        return productionBudgetModels;

    }
    @GetMapping(value = "/productionBudget" , params = "date")
    public ProductionBudgetModel getProductionBudgetByDate(@RequestParam("date")Date date)
    {
        ProductionBudgetModel productionBudgetModel ;
        productionBudgetModel = productionBudgetService.findProductionBudgetByProductionDate(date);
        return productionBudgetModel;

    }
    @PostMapping(value = "/productionBudget")
    public void creatProductionBudget(@RequestBody ProductionBudgetModel productionBudgetModel)
    {
        productionBudgetService.create(productionBudgetModel);
    }


}
