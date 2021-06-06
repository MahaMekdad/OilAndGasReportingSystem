package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.models.ProductionGeneralInfoModel;
import com.iti.jets.reportingsystem.services.ProductionGeneralInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wells")
public class ProductionGeneralInfoController {

    @Autowired
    private ProductionGeneralInfoService pgiService;

    @PostMapping("/fluidLevelMeasurements")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final ProductionGeneralInfoModel productionGeneralInfoModel){
//        pgiService.create(productionGeneralInfoModel);
    }
}
