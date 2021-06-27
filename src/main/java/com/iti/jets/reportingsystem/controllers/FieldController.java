package com.iti.jets.reportingsystem.controllers;


import com.iti.jets.openapi.api.FieldsApi;
import com.iti.jets.openapi.model.*;
import com.iti.jets.reportingsystem.services.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class FieldController implements FieldsApi {

    private final FieldService fieldService;

    @Autowired
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @Override
    public ResponseEntity<List<FieldResponse>> getfields() {
        List<FieldResponse> responseList = fieldService.getAllFields();
        System.out.println("list in controller == "+ responseList);
        return ResponseEntity.ok(responseList);
    }

//    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isWellConcessionMember(#fieldId))")
    @Override
    public ResponseEntity<FieldResponse> getfieldById(Long fieldId) {

        return ResponseEntity.ok(fieldService.getFieldByID(Math.toIntExact(fieldId)));
    }


    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isWellConcessionMember(#id))")
    @Override
    public ResponseEntity<Void> deletefieldById(Integer id) {
        fieldService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isWellConcessionMember(#fieldId))")
    @Override
    public ResponseEntity<Void> updatefieldMeasurement(Long fieldId, @Valid FieldRequest fieldRequest) {
        FieldResponse fieldResponse = null;
        try {
            fieldService.update(Math.toIntExact(fieldId), fieldRequest);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasRole('OFFICE ENGINEER') or (hasRole('FIELD ENGINEER') and @mySecurityService.isConcessionMember(#fieldRequest.concessionId()))")
    @Override
    public ResponseEntity<Void> addfieldMeasurement(@Valid FieldRequest fieldRequest) {
        fieldService.insert(fieldRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}





