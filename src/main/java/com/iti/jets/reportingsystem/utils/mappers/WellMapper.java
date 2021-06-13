package com.iti.jets.reportingsystem.utils.mappers;

import com.iti.jets.openapi.model.LabMeasurementRequest;
import com.iti.jets.openapi.model.WellRequest;
import com.iti.jets.reportingsystem.entities.LabMesurement;
import com.iti.jets.reportingsystem.entities.Well;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class WellMapper {

    public Well WellMap(WellRequest wellRequest) {
        Well well= new Well();
        well.setWellCode(wellRequest.getWellCode());
        well.setWellName(wellRequest.getWellName());
        return well;
    }






}
