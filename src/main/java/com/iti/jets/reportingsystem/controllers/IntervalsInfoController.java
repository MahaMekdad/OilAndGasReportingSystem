package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.entities.IntervalsInfo;
import com.iti.jets.reportingsystem.entities.WellGeneralInfo;
import com.iti.jets.reportingsystem.services.impls.IntervalsInfoService;
import com.iti.jets.reportingsystem.services.impls.WellGeneralInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IntervalsInfoController {
    private IntervalsInfoService intervalsInfoService;


    @Autowired
    public IntervalsInfoController(IntervalsInfoService intervalsInfoService){
        this.intervalsInfoService=intervalsInfoService;
    }

    @GetMapping("/wells/intervalsInformation")
    public ResponseEntity<List<IntervalsInfo>> getAllWellsGeneralInfo(){
        return ResponseEntity.ok(intervalsInfoService.getAllIntervalsInfo());
    }


    @GetMapping("/wells/{id}/intervalsInformation")
    public ResponseEntity<IntervalsInfo> getWellGeneralInfoById(@PathVariable  int id){
        IntervalsInfo intervalsInfo=intervalsInfoService.getIntervalsInfoById(id);
        if (intervalsInfo==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(intervalsInfo);
    }

    @PostMapping("/wells/intervalsInformation")
    public ResponseEntity saveIntervalsInfo(@RequestBody IntervalsInfo intervalsInfo){
        intervalsInfoService.saveIntervalsInfo(intervalsInfo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/wells/intervalsInformation")
    public ResponseEntity updateIntervalsInfo(@RequestBody IntervalsInfo intervalsInfo){
        if(intervalsInfoService.updateIntervalsInfo(intervalsInfo.getId(),intervalsInfo)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/wells/{id}/intervalsInformation")
    public ResponseEntity deleteIntervalsInfo(int id){
        if(intervalsInfoService.deleteIntervalsInfo(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }



}
