package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.FieldRequest;
import com.iti.jets.openapi.model.FieldResponse;


import java.util.List;

public interface FieldService {

    void insert( FieldRequest fieldRequest);

    void update(int i, FieldRequest fieldRequest);

    List<FieldResponse> getAllFields();

    FieldResponse getFieldByID(Integer fieldId);


    public boolean delete(Integer fieldId );

}
