package com.iti.jets.reportingsystem.utils.mappers;

import com.iti.jets.openapi.model.LabMeasurementRequest;
import com.iti.jets.reportingsystem.entities.LabMesurement;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class LabMeasurementMapper {

    public LabMesurement labMesurementMap(LabMeasurementRequest labMeasurementRequest) {
        LabMesurement labMeasurement = new LabMesurement();
        labMeasurement.setDate(Date.from(labMeasurementRequest.getDate().toInstant()));
        labMeasurement.setS1(labMeasurementRequest.getS1());
        labMeasurement.setS2(labMeasurementRequest.getS2());
        labMeasurement.setS3(labMeasurementRequest.getS3());
        labMeasurement.setS4(labMeasurementRequest.getS4());
        labMeasurement.setS5(labMeasurementRequest.getS5());
        labMeasurement.setRemarks(labMeasurementRequest.getRemarks());
        return labMeasurement;
    }






}
