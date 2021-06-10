package com.iti.jets.reportingsystem.utils.mappers;

import com.iti.jets.openapi.model.FluidLevelMeasurementRequest;
import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FluidLevelMeasurementsMapper {
    public FluidLevelMeasurements map(FluidLevelMeasurementRequest flmRequest) {
        FluidLevelMeasurements flm = new FluidLevelMeasurements();
        flm.setRemarks(flmRequest.getRemarks());
        flm.setCard(flmRequest.getCard().getValue().charAt(0)); flm.setFluidLevel(flmRequest.getFluidLevel().doubleValue());
        flm.setPumpSubmerge(flmRequest.getPumpSubmerge().doubleValue()); flm.setPumpDepth(flmRequest.getPumpDepth().doubleValue());
        flm.setLiqPercentage(flmRequest.getLiqPercentage().longValue()); flm.setFlType(flmRequest.getFlType().getValue());
        flm.setDate(Date.from(flmRequest.getDate().toInstant())); flm.setIntervals(flmRequest.getIntervals());
        flm.setPumpFillage(flmRequest.getPumpFillage().longValue());
        return flm;
    }

    public FluidLevelMeasurements mapForPatch(FluidLevelMeasurementRequest flmRequest, FluidLevelMeasurements flmObjToUpdate) {
        if(flmRequest.getRemarks() != null){
            flmObjToUpdate.setRemarks(flmRequest.getRemarks());
        }
        if(flmRequest.getCard() != null){
            flmObjToUpdate.setCard(flmRequest.getCard().getValue().charAt(0));
        }
        if(flmRequest.getFluidLevel() != null){
            flmObjToUpdate.setFluidLevel(flmRequest.getFluidLevel().doubleValue());
        }
        if(flmRequest.getPumpSubmerge() != null){
            flmObjToUpdate.setPumpSubmerge(flmRequest.getPumpSubmerge().doubleValue());
        }
        if(flmRequest.getPumpDepth() != null){
            flmObjToUpdate.setPumpDepth(flmRequest.getPumpDepth().doubleValue());
        }
        if(flmRequest.getLiqPercentage() != null){
            flmObjToUpdate.setLiqPercentage(flmRequest.getLiqPercentage().longValue());
        }
        if(flmRequest.getFlType() != null){
            flmObjToUpdate.setFlType(flmRequest.getFlType().getValue());
        }
        if(flmRequest.getDate() != null){
            flmObjToUpdate.setDate(Date.from(flmRequest.getDate().toInstant()));
        }
        if(flmRequest.getIntervals() != null){
            flmObjToUpdate.setIntervals(flmRequest.getIntervals());
        }
        if(flmRequest.getPumpFillage() != null){
            flmObjToUpdate.setPumpFillage(flmRequest.getPumpFillage().longValue());
        }
        return flmObjToUpdate;
    }
}
