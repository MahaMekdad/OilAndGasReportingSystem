package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import com.iti.jets.reportingsystem.services.FluidLevelMeasurementsService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wells")
public class FluidLevelMeasurementsController {

    @Autowired
    private FluidLevelMeasurementsService flmService;

    @PostMapping("/fluidLevelMeasurements")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody FluidLevelMeasurementsModel fluidLevelMeasurementsModel){
        System.out.println(fluidLevelMeasurementsModel.getWellId() + " $$$$$ ");
        flmService.insert(fluidLevelMeasurementsModel);
    }

    @GetMapping(value = "/fluidLevelMeasurements")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FluidLevelMeasurementsModel> getAllFluidLevelMeasurements(){
        return flmService.getAllFLMS();
    }

    @GetMapping(value = "{wellId}/fluidLevelMeasurements")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FluidLevelMeasurementsModel> getAllWellFluidLevelMeasurements(@PathVariable int wellId){
        return flmService.getAllFLMSForAWell(wellId);
    }

    @GetMapping(value = "{wellId}/fluidLevelMeasurements", params = {"beginDate", "endDate"})
    @ResponseStatus(HttpStatus.CREATED)
    public List<FluidLevelMeasurementsModel> getAllWellFluidLevelMeasurements(@PathVariable int wellId, @RequestParam("beginDate") Date beginDate, @RequestParam("endDate") Date endDate){
        return flmService.getAllFLMSForAWell(wellId);
    }

    @PutMapping("{wellId}/fluidLevelMeasurements/{flmId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void fullWellFluidLevelMeasurementUpdate(@PathVariable int wellId, @PathVariable int flmId, @RequestBody final FluidLevelMeasurementsModel fluidLevelMeasurementsModel){
        flmService.updateSpecificFLMS(wellId, flmId, fluidLevelMeasurementsModel);
    }

    @DeleteMapping("{wellId}/fluidLevelMeasurements/{flmId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteWellFluidLevelMeasurement(@PathVariable int wellId, @PathVariable int flmId){
        flmService.deleteSpecificFLMS(wellId, flmId);
    }
}
