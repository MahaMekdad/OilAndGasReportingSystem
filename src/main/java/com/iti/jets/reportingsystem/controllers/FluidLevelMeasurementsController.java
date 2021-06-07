package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import com.iti.jets.reportingsystem.services.FluidLevelMeasurementsService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wells")
public class FluidLevelMeasurementsController {

    @Autowired
    private FluidLevelMeasurementsService flmService;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/fluidLevelMeasurements")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final FluidLevelMeasurementsModel fluidLevelMeasurementsModel){
        flmService.insert(modelMapper.map(fluidLevelMeasurementsModel, FluidLevelMeasurements.class));
    }

//    @GetMapping("{wellId}/fluidLevelMeasurements")
//    @ResponseStatus(HttpStatus.CREATED)
//    public List<FluidLevelMeasurementsModel> getAllWellFluidLevelMeasurements(@PathVariable int wellId){
//        List<FluidLevelMeasurements> flmRecords = flmService.getAllFLMSForAWell(wellId);
//        Type listType = new TypeToken<List<FluidLevelMeasurementsModel>>(){}.getType();
//        List<FluidLevelMeasurementsModel> resultList = new ArrayList<>();
//        resultList = modelMapper.map(flmRecords ,listType );
//        System.out.println("----------------- "+resultList);
//
////        List<FluidLevelMeasurementsModel> resultList =
////                flmRecords
////                        .stream()
////                        .map(flm -> modelMapper.map(flm, FluidLevelMeasurementsModel.class))
////                        .collect(Collectors.toList());
//        return  resultList;
//    }

//    @PutMapping("{wellId}/fluidLevelMeasurements/{flmId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void fullWellFluidLevelMeasurement(@PathVariable int wellId, @PathVariable int flmId, @RequestBody final FluidLevelMeasurementsModel fluidLevelMeasurementsModel){
//        flmService.updateSpecificFLMS(wellId, flmId, modelMapper.map(fluidLevelMeasurementsModel, FluidLevelMeasurements.class));
//    }
//
//    @PatchMapping("{wellId}/fluidLevelMeasurements/{flmId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void partialWellFluidLevelMeasurementUpdate(@PathVariable int wellId, @PathVariable int flmId, @RequestBody final FluidLevelMeasurementsModel fluidLevelMeasurementsModel){
//        FluidLevelMeasurements toUpdate = new FluidLevelMeasurements();
//        toUpdate.setId(flmId);
//        toUpdate.setWell(fluidLevelMeasurementsModel.getWell());
//        if(fluidLevelMeasurementsModel.getDate() != null) toUpdate.setDate(fluidLevelMeasurementsModel.getDate());
//        if(fluidLevelMeasurementsModel.getIntervals() != null) toUpdate.setIntervals(fluidLevelMeasurementsModel.getIntervals());
//        if(fluidLevelMeasurementsModel.getFltype() != null) toUpdate.setFltype(fluidLevelMeasurementsModel.getFltype());
//        if(fluidLevelMeasurementsModel.getFluidlLevel() != null) toUpdate.setFluidlLevel(fluidLevelMeasurementsModel.getFluidlLevel());
//        if(fluidLevelMeasurementsModel.getPumpDepth() != null) toUpdate.setPumpDepth(fluidLevelMeasurementsModel.getPumpDepth());
//        if(fluidLevelMeasurementsModel.getPumpSubmerge() != null) toUpdate.setPumpSubmerge(fluidLevelMeasurementsModel.getPumpSubmerge());
//        if(fluidLevelMeasurementsModel.getCard() != null) toUpdate.setCard(fluidLevelMeasurementsModel.getCard());
//        if(fluidLevelMeasurementsModel.getRemarks() != null) toUpdate.setRemarks(fluidLevelMeasurementsModel.getRemarks());
//        flmService.updateSpecificFLMS(wellId, flmId, toUpdate);
//    }
//
//    @DeleteMapping("{wellId}/fluidLevelMeasurements/{flmId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void deleteWellFluidLevelMeasurement(@PathVariable int flmId){
//        flmService.delete(flmId);
//    }




}