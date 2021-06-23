package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.openapi.api.ConcessionFieldsApi;
import com.iti.jets.openapi.model.ConcessionsFieldsResponse;
import com.iti.jets.reportingsystem.services.BudgetVsActualService;
import com.iti.jets.reportingsystem.services.FieldsAndWellsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@RestController
public class ConcessionFieldController implements ConcessionFieldsApi {

    private final FieldsAndWellsService fieldsAndWellsService;

    @Autowired
    ConcessionFieldController(FieldsAndWellsService fieldsAndWellsService) {
        this.fieldsAndWellsService = fieldsAndWellsService;
    }

    @Override
    public ResponseEntity<List<ConcessionsFieldsResponse>> concessionFieldsIdGet(Integer id) {
        List<ConcessionsFieldsResponse> concessionsFieldsResponseList =
                fieldsAndWellsService.getAllFieldsForConcession(id);
        if (concessionsFieldsResponseList == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok(concessionsFieldsResponseList);
        }

    }
}
