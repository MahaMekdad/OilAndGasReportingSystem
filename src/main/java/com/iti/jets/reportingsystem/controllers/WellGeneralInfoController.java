package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.entities.WellGeneralInfo;
import com.iti.jets.reportingsystem.services.impls.WellGeneralInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WellGeneralInfoController {
    private WellGeneralInfoService wellGeneralInfoService;


    @Autowired
    public WellGeneralInfoController(WellGeneralInfoService wellGeneralInfoService){
        this.wellGeneralInfoService=wellGeneralInfoService;
    }

    @GetMapping("/wells/GeneralInformation")
    public ResponseEntity<List<WellGeneralInfo>> getAllWellsGeneralInfo(){
        return ResponseEntity.ok(wellGeneralInfoService.getAllWellsGeneralInfo());
    }


    @GetMapping("/wells/{id}/GeneralInformation")
    public ResponseEntity<WellGeneralInfo> getWellGeneralInfoById(@PathVariable  int id){
        WellGeneralInfo wellGeneralInfo=wellGeneralInfoService.getWellGeneralInfoById(id);
        if (wellGeneralInfo==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(wellGeneralInfo);
    }

    @PostMapping("/wells/GeneralInformation")
    public ResponseEntity saveWellGeneralInfo(@RequestBody WellGeneralInfo wellGeneralInfo){
        wellGeneralInfoService.saveWellGeneralInfo(wellGeneralInfo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/wells/GeneralInformation")
    public ResponseEntity updateWellGeneralInfo(@RequestBody WellGeneralInfo wellGeneralInfo){
        if(wellGeneralInfoService.updateWellGeneralInfo(wellGeneralInfo.getId(),wellGeneralInfo)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/wells/{id}/GeneralInformation")
    public ResponseEntity deleteWellGeneralInfo(int id){
        if(wellGeneralInfoService.deleteWellGeneralInfo(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }



}
