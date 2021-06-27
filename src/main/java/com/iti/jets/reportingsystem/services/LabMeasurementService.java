package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.LabMeasurementRequest;
import com.iti.jets.openapi.model.LabMeasurementResponse;
import com.iti.jets.reportingsystem.entities.LabMesurement;

import java.util.Date;
import java.util.List;

public interface LabMeasurementService {

    void insert(Integer wellId , LabMeasurementRequest labMeasurementRequest);

    void update(Integer wellId , Integer labId , LabMeasurementRequest labMeasurementRequest);

    List<LabMeasurementResponse> getAllLabs();

    List<LabMeasurementResponse> getAllLabsFromWell(Integer wellId);

    LabMeasurementResponse getAlabFromAwell(Integer wellId , Integer labId);

    List<LabMeasurementResponse> getAllLabs (Date beginDate, Date endDate);

    List<LabMeasurementResponse> getAllLabsFromWell(Integer wellId, Date beginDate, Date endDate);


    public boolean delete(Integer wellId , Integer labId);

}
