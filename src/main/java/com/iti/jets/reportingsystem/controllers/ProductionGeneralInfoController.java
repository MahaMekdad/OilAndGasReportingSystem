package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;
import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import com.iti.jets.reportingsystem.models.ProductionGeneralInfoModel;
import com.iti.jets.reportingsystem.services.ProductionGeneralInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
    @RequestMapping("/wells")
public class ProductionGeneralInfoController {

    @Autowired
    private ProductionGeneralInfoService pgiService;

    private ModelMapper modelMapper = new ModelMapper();

//    @PostMapping("/productionGeneralInfo")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void create(@RequestBody final ProductionGeneralInfoModel productionGeneralInfoModel){
//       pgiService.create(productionGeneralInfoModel);
//    }

    @GetMapping("{wellId}/productionGeneralInfo")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductionGeneralInfoModel> getAllWellFluidLevelMeasurements(@PathVariable int wellId){
        List<ProductionGeneralInfo> pgiRecords = pgiService.getAllPGISForAWell(wellId);
        List<ProductionGeneralInfoModel> resultList =
                pgiRecords
                        .stream()
                        .map(flm -> modelMapper.map(flm, ProductionGeneralInfoModel.class))
                        .collect(Collectors.toList());
        return  resultList;
    }
}
