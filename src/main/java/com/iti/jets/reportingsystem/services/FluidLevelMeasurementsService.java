package com.iti.jets.reportingsystem.services;

import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface FluidLevelMeasurementsService {

    void delete(int flmId);

    void insert(FluidLevelMeasurementsModel fluidLevelMeasurementModel);

    List<FluidLevelMeasurementsModel> getAllFLMS();

    List<FluidLevelMeasurementsModel> getAllFLMS(Date beginDate, Date endDate);

    List<FluidLevelMeasurementsModel> getAllFLMSForAWell(int wellId);

    List<FluidLevelMeasurementsModel> getAllFLMSForAWell(int wellId, Date beginDate, Date endDate);

//    public void updateAllFLMSForAWell(int wellId, FluidLevelMeasurements fluidLevelMeasurement);
//
//    public void deleteAllFLMSForAWell(int wellId);

    void updateSpecificFLMS(int wellId, int flmId, FluidLevelMeasurementsModel fluidLevelMeasurementModel);

    void deleteSpecificFLMS(int wellId, int flmId);
}
