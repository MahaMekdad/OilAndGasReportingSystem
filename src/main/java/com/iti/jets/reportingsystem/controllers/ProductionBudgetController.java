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
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Date date2 = (Date) formatter.parse(date.toString());
        System.out.println("date222222 " + date2);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date2);
        String formatedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR);
        String[] splitString = formatedDate.split("-");
        String day = null;
        String month = null;
        if (splitString[0].length() == 1) {
            day = "0" + splitString[0];
        } else {
            day = splitString[0];
        }
        if (splitString[1].length() == 1) {
            month = "0" + splitString[1];
        } else {
            month = splitString[1];
        }
        String finalDate = cal.get(Calendar.YEAR)+ "-" +month+"-"+ day ;
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK)+1;
//        int month =  c.get(Calendar.MONTH)+1;
//        int year = c.get(Calendar.YEAR)+1;
//        System.out.println("day "+ dayOfWeek);
//        System.out.println("month "+ month);
//        System.out.println("day "+ dayOfWeek);
        ProductionBudgetModel productionBudgetModel;
        productionBudgetModel = productionBudgetService.findProductionBudgetByProductionDate(finalDate);
        return productionBudgetModel;

    }

    @GetMapping(value = "/productionBudget", params = "id")
    public ProductionBudgetModel getProductionBudgetByDatej(@RequestParam("id") int id) {
        System.out.println("id " + id);
        ProductionBudgetModel productionBudgetModel;
        productionBudgetModel = productionBudgetService.findProductionBudgetById(id);
        return productionBudgetModel;


    }

    @PostMapping(value = "/productionBudget")
    public void creatProductionBudget(@RequestBody ProductionBudgetModel productionBudgetModel) {
        productionBudgetService.create(productionBudgetModel);
    }
    @DeleteMapping(value = "productionBudget/{id}")
    void deleteBudget(@PathVariable int id)
    {
        productionBudgetService.delete(id);
    }


}
