package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.FieldRequest;
import com.iti.jets.openapi.model.FieldResponse;
import com.iti.jets.openapi.model.WellRequest;
import com.iti.jets.openapi.model.WellResponse;


import java.util.List;

public interface FieldService {

    FieldResponse insert( FieldRequest fieldRequest);
    FieldResponse update(int id, FieldRequest fieldRequest);
    List<FieldResponse> getAllFields();
    FieldResponse getFieldByID(Integer fieldId);
    public boolean delete(Integer fieldId );


}
