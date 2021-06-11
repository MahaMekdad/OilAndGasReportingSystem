package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.WellsApi;
import com.iti.jets.openapi.model.DrillingInfoDataRequest;
import com.iti.jets.openapi.model.DrillingInfoDataResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class DrillingInfoController implements WellsApi {
    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoPost(Integer wellId, List<DrillingInfoDataRequest> drillingInfoDataRequest) {
        return WellsApi.super.wellsWellIdDrillingInfoPost(wellId, drillingInfoDataRequest);
    }

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoDelete(Integer wellId) {
        return WellsApi.super.wellsWellIdDrillingInfoDelete(wellId);
    }

    @Override
    public ResponseEntity<List<DrillingInfoDataResponse>> wellsWellIdDrillingInfoGet(Integer wellId) {
        return WellsApi.super.wellsWellIdDrillingInfoGet(wellId);
    }

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoIdDelete(Integer wellId, Integer id) {
        return WellsApi.super.wellsWellIdDrillingInfoIdDelete(wellId, id);
    }

    @Override
    public ResponseEntity<List<DrillingInfoDataResponse>> wellsWellIdDrillingInfoIdGet(Integer wellId, Integer id) {
        return WellsApi.super.wellsWellIdDrillingInfoIdGet(wellId, id);
    }

    @Override
    public ResponseEntity<Void> wellsWellIdDrillingInfoIdPatch(Integer wellId, Integer id, List<DrillingInfoDataRequest> drillingInfoDataRequest) {
        return WellsApi.super.wellsWellIdDrillingInfoIdPatch(wellId, id, drillingInfoDataRequest);
    }
}
