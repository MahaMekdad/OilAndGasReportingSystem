package com.iti.jets.reportingsystem.controllers;


import com.iti.jets.openapi.api.WellsApi;
import com.iti.jets.openapi.model.*;
import com.iti.jets.reportingsystem.services.WellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WellController implements WellsApi {

private WellService wellService;
    @Autowired
    public WellController(WellService wellService) {
        this.wellService = wellService;
    }
    @Override
    public ResponseEntity<List<WellResponse>> getwells() {
        List<WellResponse> responseList = wellService.getAllWells();
        return ResponseEntity.ok(responseList);
        }

    @Override
    public ResponseEntity<WellResponse> getwellById(Long wellId) {

            return ResponseEntity.ok(wellService.getWellByID(Math.toIntExact(wellId)));
        }

    @Override
    public ResponseEntity<Void> deleteWellById(Integer id) {
        wellService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateWell(Long wellId, @Valid WellRequest wellRequest) {
        wellService.update(Math.toIntExact(wellId), wellRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addwell( @Valid WellRequest wellRequest) {
        wellService.insert(wellRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    }





