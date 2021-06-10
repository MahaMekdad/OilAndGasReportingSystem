//package com.iti.jets.reportingsystem.controllers;
//
//import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
//import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
//import com.iti.jets.reportingsystem.services.FluidLevelMeasurementsService;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@RestController
//@RequestMapping("/wells")
//public class FluidLevelMeasurementsControllerOld {
//
//    @Autowired
//    private FluidLevelMeasurementsService flmService;
//
//    @PostMapping("/fluidLevelMeasurements")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void create(@RequestBody FluidLevelMeasurementsModel fluidLevelMeasurementsModel){
//        System.out.println(fluidLevelMeasurementsModel.getWellId() + " $$$$$ ");
//        flmService.insert(fluidLevelMeasurementsModel);
//    }
//
//    @GetMapping(value = "/fluidLevelMeasurements")
//    @ResponseStatus(HttpStatus.CREATED)
//    public List<FluidLevelMeasurementsModel> getAllFluidLevelMeasurements(){
//        return flmService.getAllFLMS();
//    }
//
//    @GetMapping(value = "/fluidLevelMeasurements", params = {"beginDate", "endDate"})
//    @ResponseStatus(HttpStatus.CREATED)
//    public List<FluidLevelMeasurementsModel> getAllFluidLevelMeasurements(@RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate){
//        try {
//            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse(beginDate);
//            System.out.println(begin + "<======");
//            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
//            return flmService.getAllFLMS(begin, end);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @GetMapping(value = "{wellId}/fluidLevelMeasurements")
//    @ResponseStatus(HttpStatus.CREATED)
//    public List<FluidLevelMeasurementsModel> getAllWellFluidLevelMeasurements(@PathVariable int wellId){
//        return flmService.getAllFLMSForAWell(wellId);
//    }
//
//    @GetMapping(value = "{wellId}/fluidLevelMeasurements", params = {"beginDate", "endDate"})
//    @ResponseStatus(HttpStatus.CREATED)
//    public List<FluidLevelMeasurementsModel> getAllWellFluidLevelMeasurements(@PathVariable int wellId, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate){
//        System.out.println(beginDate + "<======");
//        try {
//            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse(beginDate);
//            System.out.println(begin + "<======");
//            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
//            return flmService.getAllFLMSForAWell(wellId, begin, end);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @PutMapping("{wellId}/fluidLevelMeasurements/{flmId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void fullWellFluidLevelMeasurementUpdate(@PathVariable int wellId, @PathVariable int flmId, @RequestBody final FluidLevelMeasurementsModel fluidLevelMeasurementsModel){
//        flmService.updateSpecificFLMS(wellId, flmId, fluidLevelMeasurementsModel);
//    }
//
//    @DeleteMapping("{wellId}/fluidLevelMeasurements/{flmId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void deleteWellFluidLevelMeasurement(@PathVariable int wellId, @PathVariable int flmId){
//        flmService.deleteSpecificFLMS(wellId, flmId);
//    }
//}
