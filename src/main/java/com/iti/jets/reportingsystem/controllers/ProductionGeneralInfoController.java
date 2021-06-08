package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import com.iti.jets.reportingsystem.models.ProductionGeneralInfoModel;
import com.iti.jets.reportingsystem.services.ProductionGeneralInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
    @RequestMapping("/wells")
public class ProductionGeneralInfoController {

    @Autowired
    private ProductionGeneralInfoService pgiService;

    @PostMapping("/productionGeneralInfo")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductionGeneralInfoModel productionGeneralInfoModel){
        System.out.println(productionGeneralInfoModel.getWellId() + " $$$$$ ");
        pgiService.insert(productionGeneralInfoModel);
    }

    @GetMapping(value = "/productionGeneralInfo")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductionGeneralInfoModel> getAllProductionGeneralInfoModel(){
        return pgiService.getAllPGIS();
    }

    @GetMapping(value = "{wellId}/productionGeneralInfo")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductionGeneralInfoModel> getAllWellProductionLGeneralInfo(@PathVariable int wellId){
        return pgiService.getAllPGISForAWell(wellId);
    }

    @GetMapping(value = "{wellId}/productionGeneralInfo", params = {"beginDate", "endDate"})
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductionGeneralInfoModel> getAllWellProductionLGeneralInfo(@PathVariable int wellId, @RequestParam("beginDate") Date beginDate, @RequestParam("endDate") Date endDate){
        return pgiService.getAllPGISForAWell(wellId);
    }

    @PutMapping("{wellId}/productionGeneralInfo/{pgiId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void wellProductionLGeneralInfoUpdate(@PathVariable int wellId, @PathVariable int pgiId, @RequestBody final ProductionGeneralInfoModel productionGeneralInfoModel){
        pgiService.updateSpecificPGIS(wellId, pgiId, productionGeneralInfoModel);
    }

    @DeleteMapping("{wellId}/productionGeneralInfo/{pgiId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteWellProductionLGeneralInfo(@PathVariable int wellId, @PathVariable int pgiId){
        pgiService.deleteSpecificPGIS(wellId, pgiId);
    }
}
