package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.WellsApi;
import com.iti.jets.openapi.model.DrillingInfoDataRequest;
import com.iti.jets.openapi.model.DrillingInfoDataResponse;
import com.iti.jets.reportingsystem.services.DrillingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class DrillingInfoController implements WellsApi {
    @Autowired
    private DrillingInfoService drillingInfoService;

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoDelete(Integer wellId) {
        drillingInfoService.delete(wellId);
    }

    @Override
    public ResponseEntity<List<DrillingInfoDataResponse>> wellsWellIdDrillingInfoGet(Integer wellId) {
        List<DrillingInfoDataResponse> drillingInfoDataResponses = drillingInfoService.getForWellId(wellId);
        return drillingInfoDataResponses;
    }

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoIdDelete(Integer wellId, Integer id) {
        drillingInfoService.deleteWellInSpecificId(wellId , id);
    }

    @Override
    public ResponseEntity<List<DrillingInfoDataResponse>> wellsWellIdDrillingInfoIdGet(Integer wellId, Integer id) {
         drillingInfoService.getWellForId(wellId , id);
    }

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoIdPatch(Integer wellId, Integer id, DrillingInfoDataRequest drillingInfoDataRequest) {
        drillingInfoService.updateWellForId(wellId ,id ,drillingInfoDataRequest);
    }

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoPost(Integer wellId, DrillingInfoDataRequest drillingInfoDataRequest) {
        drillingInfoService.creat(drillingInfoDataRequest , wellId);
        return ResponseEntity.ok();
    }

    @Override
    public ResponseEntity<List<DrillingInfoDataResponse>> wellsDrillingInfoGet() {
        List<DrillingInfoDataResponse> drillingInfoDataResponses = new ArrayList<>();
        drillingInfoDataResponses = drillingInfoService.getAllDrillingInfo();
        return drillingInfoDataResponses;
    }

}
