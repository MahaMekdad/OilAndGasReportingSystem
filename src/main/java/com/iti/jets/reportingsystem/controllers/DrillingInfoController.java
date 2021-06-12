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

//@RestController
public class DrillingInfoController implements WellsApi {
    @Autowired
    private DrillingInfoService drillingInfoService;

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoDelete(Integer wellId) {
        drillingInfoService.delete(wellId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<DrillingInfoDataResponse>> wellsWellIdDrillingInfoGet(Integer wellId) {
        List<DrillingInfoDataResponse> drillingInfoDataResponses = drillingInfoService.getForWellId(wellId);
        if (drillingInfoDataResponses == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok(drillingInfoDataResponses);
        }
    }

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoIdDelete(Integer wellId, Integer id) {
        drillingInfoService.deleteWellInSpecificId(wellId , id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<DrillingInfoDataResponse> wellsWellIdDrillingInfoIdGet(Integer wellId, Integer id) {
        DrillingInfoDataResponse drillingInfoDataResponse =drillingInfoService.getWellForId(wellId , id);
        if(drillingInfoDataResponse == null)
        {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(drillingInfoDataResponse);
        }
    }

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoIdPatch(Integer wellId, Integer id, DrillingInfoDataRequest drillingInfoDataRequest) {
        drillingInfoService.updateWellForId(wellId ,id ,drillingInfoDataRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoPost(Integer wellId, DrillingInfoDataRequest drillingInfoDataRequest) {
        drillingInfoService.creat(drillingInfoDataRequest, wellId);
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<List<DrillingInfoDataResponse>> wellsDrillingInfoGet() {
        List<DrillingInfoDataResponse> drillingInfoDataResponses = new ArrayList<>();
        drillingInfoDataResponses = drillingInfoService.getAllDrillingInfo();
        if (drillingInfoDataResponses == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok(drillingInfoDataResponses);
        }

    }


}
