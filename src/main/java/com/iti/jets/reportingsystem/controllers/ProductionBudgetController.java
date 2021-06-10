package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.entities.ProductionBudget;
import com.iti.jets.reportingsystem.models.ProductionBudgetModel;
import com.iti.jets.reportingsystem.repos.ProductionBudgetRepository;
import com.iti.jets.reportingsystem.services.ProductionBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/concessions/budget")
public class ProductionBudgetController {
    @Autowired
    private ProductionBudgetService productionBudgetService;

    @GetMapping("/productionBudget")
    public List<ProductionBudgetModel> getAllProductionBudget() {
        List<ProductionBudgetModel> productionBudgetModels = new ArrayList<>();
        productionBudgetModels = productionBudgetService.getGetAllProductionBudget();
        return productionBudgetModels;
    }

    @GetMapping(value = "/productionBudget", params = "date")
    public ProductionBudgetModel getProductionBudgetByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) throws ParseException {
        System.out.println("datee " + date);
        ProductionBudgetModel productionBudgetModel;
        productionBudgetModel = productionBudgetService.findProductionBudgetByProductionDate(date);
        return productionBudgetModel;

    }

//    @GetMapping(value = "/productionBudget", params = "id")
//    public ProductionBudgetModel getProductionBudgetByDate(@RequestParam("id") int id) {
//        System.out.println("id " + id);
//        ProductionBudgetModel productionBudgetModel;
//        productionBudgetModel = productionBudgetService.findProductionBudgetById(id);
//        return productionBudgetModel;
//
//
//    }

    @PostMapping(value = "/productionBudget")
    public void creatProductionBudget(@RequestBody ProductionBudgetModel productionBudgetModel) {
        productionBudgetService.create(productionBudgetModel);
    }
    @DeleteMapping(value = "productionBudget/{id}")
    void deleteBudget(@PathVariable int id)
    {
        productionBudgetService.delete(id);
    }

    @PutMapping(value = "productionBudget/{id}")
    void update(@PathVariable int id , @RequestBody ProductionBudgetModel productionBudgetModel)
    {
        productionBudgetService.updateProductionBudget(id ,productionBudgetModel);
    }


}
