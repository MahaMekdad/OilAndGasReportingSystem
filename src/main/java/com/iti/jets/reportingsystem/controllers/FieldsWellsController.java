package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.FieldWellsApi;
import com.iti.jets.openapi.model.ConcessionsFieldsResponse;
import com.iti.jets.openapi.model.FieldsWellsResponse;
import com.iti.jets.reportingsystem.services.FieldsAndWellsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class FieldsWellsController implements FieldWellsApi {
    private final FieldsAndWellsService fieldsAndWellsService;

    @Autowired
    FieldsWellsController(FieldsAndWellsService fieldsAndWellsService) {
        this.fieldsAndWellsService = fieldsAndWellsService;
    }

    @Override
    public ResponseEntity<List<FieldsWellsResponse>> fieldWellsIdGet(Integer id) {
        List<FieldsWellsResponse> fieldsWellsResponseList  = fieldsAndWellsService.getAllWellsForField(id);
        if (fieldsWellsResponseList == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok(fieldsWellsResponseList);
        }
    }
}
