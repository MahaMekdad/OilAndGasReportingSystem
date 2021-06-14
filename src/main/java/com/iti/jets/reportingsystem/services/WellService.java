package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.WellRequest;
import com.iti.jets.openapi.model.WellResponse;

import java.util.List;

public interface WellService {

    WellResponse insert( WellRequest wellRequest);

     WellResponse updateWell(int wellId, WellRequest wellRequest);
    List<WellResponse> getAllWells();

    WellResponse getWellByID(Integer wellId);


    public boolean delete(Integer wellId );

}
