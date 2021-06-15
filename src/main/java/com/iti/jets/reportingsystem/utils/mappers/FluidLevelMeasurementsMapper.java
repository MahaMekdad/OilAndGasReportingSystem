package com.iti.jets.reportingsystem.utils.mappers;

import com.iti.jets.openapi.model.AllFluidLevelMeasurementResponse;
import com.iti.jets.openapi.model.AllProductionGeneralInfoWithNamesResponse;
import com.iti.jets.openapi.model.FluidLevelMeasurementRequest;
import com.iti.jets.openapi.model.FluidLevelMeasurementResponse;
import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;
import com.iti.jets.reportingsystem.utils.mappers.helpers.OffsetDateTimeHelper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FluidLevelMeasurementsMapper {

    public List<AllFluidLevelMeasurementResponse> mapAllFlmList(List<FluidLevelMeasurements> targetList){
        List<AllFluidLevelMeasurementResponse> resultList = new ArrayList<>();
        for (FluidLevelMeasurements flm : targetList) {
            AllFluidLevelMeasurementResponse flmr = new AllFluidLevelMeasurementResponse();
            flmr.setId(flm.getId()); flmr.setWellId(flm.getWell().getWellId());
            flmr.setRemarks(flm.getRemarks());
            flmr.setCard(AllFluidLevelMeasurementResponse.CardEnum.valueOf(flm.getCard().toString().toUpperCase())); flmr.setFluidLevel(BigDecimal.valueOf(flm.getFluidLevel()));
            flmr.setPumpSubmerge(BigDecimal.valueOf(flm.getPumpSubmerge())); flmr.setPumpDepth(BigDecimal.valueOf(flm.getPumpDepth()));
            System.out.println(BigDecimal.valueOf(flm.getLiqPercentage()) + ", " + flm.getLiqPercentage());
            flmr.setLiqPercentage(BigDecimal.valueOf(flm.getLiqPercentage()));
            flmr.setFlType(AllFluidLevelMeasurementResponse.FlTypeEnum.valueOf(flm.getFlType().toUpperCase()));
//            System.out.println(OffsetDateTimeHelper.dateHelper(flm.getDate()) + "=====");
//            System.out.println(flm.getDate() + " ----");
            flmr.setDate(OffsetDateTimeHelper.dateHelper(flm.getDate()));
            flmr.setIntervals(flm.getIntervals());
            flmr.setPumpFillage(BigDecimal.valueOf(flm.getPumpFillage()));
            resultList.add(flmr);
        }
        return resultList;
    }

    public List<FluidLevelMeasurementResponse> mapFlmList(List<FluidLevelMeasurements> targetList){
        List<FluidLevelMeasurementResponse> resultList = new ArrayList<>();
        for (FluidLevelMeasurements flm : targetList) {
            FluidLevelMeasurementResponse flmr = new FluidLevelMeasurementResponse();
            flmr.setId(flm.getId());
            flmr.setRemarks(flm.getRemarks());
            flmr.setCard(FluidLevelMeasurementResponse.CardEnum.valueOf(flm.getCard().toString().toUpperCase())); flmr.setFluidLevel(BigDecimal.valueOf(flm.getFluidLevel()));
            flmr.setPumpSubmerge(BigDecimal.valueOf(flm.getPumpSubmerge())); flmr.setPumpDepth(BigDecimal.valueOf(flm.getPumpDepth()));
            flmr.setLiqPercentage(BigDecimal.valueOf(flm.getLiqPercentage())); flmr.setFlType(FluidLevelMeasurementResponse.FlTypeEnum.valueOf(flm.getFlType().toUpperCase()));
            flmr.setDate(OffsetDateTimeHelper.dateHelper(flm.getDate())); flmr.setIntervals(flm.getIntervals());
            flmr.setPumpFillage(BigDecimal.valueOf(flm.getPumpFillage()));
            resultList.add(flmr);
        }
        return resultList;
    }

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
