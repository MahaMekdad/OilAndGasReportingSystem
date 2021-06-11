package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.WellsApi;
import com.iti.jets.openapi.model.IntervalsInfoRequest;
import com.iti.jets.openapi.model.IntervalsInfoResponse;
import com.iti.jets.openapi.model.WellGeneralInfoRequest;
import com.iti.jets.openapi.model.WellGeneralInfoResponse;
import com.iti.jets.reportingsystem.entities.IntervalsInfo;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.entities.WellGeneralInfo;
import com.iti.jets.reportingsystem.services.impls.IntervalsInfoService;
import com.iti.jets.reportingsystem.services.impls.WellGeneralInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WellsController implements WellsApi {
    private WellGeneralInfoService wellGeneralInfoService;
    private IntervalsInfoService intervalsInfoService;

    @Autowired
    public WellsController(WellGeneralInfoService wellGeneralInfoService,IntervalsInfoService intervalsInfoService){
        this.intervalsInfoService=intervalsInfoService;
        this.wellGeneralInfoService=wellGeneralInfoService;
    }

// WellGeneralInfo

    @Override
    public ResponseEntity<List<WellGeneralInfoResponse>> wellsGeneralInfoGet() {
        return ResponseEntity.ok(wellGeneralInfoService.getAllWellsGeneralInfo());
    }

    @Override
    public ResponseEntity<Void> wellsGeneralInfoIdDelete(Integer id) {
        if(wellGeneralInfoService.deleteWellGeneralInfo(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<WellGeneralInfoResponse> wellsGeneralInfoIdGet(Integer id) {
        WellGeneralInfoResponse wellGeneralInfoResponse=wellGeneralInfoService.getWellGeneralInfoById(id);
        if (wellGeneralInfoResponse==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(wellGeneralInfoResponse);
    }

    @Override
    public ResponseEntity<Void> wellsGeneralInfoIdPut(Integer id, @Valid WellGeneralInfoRequest wellGeneralInfoRequest) {
        if(wellGeneralInfoService.updateWellGeneralInfo(id,wellGeneralInfoRequest)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> wellsGeneralInfoPost(@Valid WellGeneralInfoRequest wellGeneralInfoRequest) {
        WellGeneralInfo wellGeneralInfo=wellGeneralInfoService.saveWellGeneralInfo(wellGeneralInfoRequest);
        if(wellGeneralInfo==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


   //Intervals Information

    @Override
    public ResponseEntity<List<IntervalsInfoResponse>> wellsIntervalsInfoGet() {
        return ResponseEntity.ok(intervalsInfoService.getAllIntervalsInfo());
    }

    @Override
    public ResponseEntity<Void> wellsIntervalsInfoIdDelete(Integer id) {
        if(intervalsInfoService.deleteIntervalsInfo(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<IntervalsInfoResponse> wellsIntervalsInfoIdGet(Integer id) {
        IntervalsInfoResponse intervalsInfoResponse=intervalsInfoService.getIntervalsInfoById(id);
        if (intervalsInfoResponse==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(intervalsInfoResponse);
    }

    @Override
    public ResponseEntity<Void> wellsIntervalsInfoIdPut(Integer id, @Valid IntervalsInfoRequest intervalsInfoRequest) {
        if(intervalsInfoService.updateIntervalsInfo(id,intervalsInfoRequest)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> wellsIntervalsInfoPost(@Valid IntervalsInfoRequest intervalsInfoRequest) {
        IntervalsInfo intervalsInfo=intervalsInfoService.saveIntervalsInfo(intervalsInfoRequest);
        if(intervalsInfo==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
