package com.iti.jets.reportingsystem.services;

import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FluidLevelMeasurementsService {

    public void insert(FluidLevelMeasurements fluidLevelMeasurement);

    public List<FluidLevelMeasurements> getAllFLMSForAWell(int wellId);

    public void updateAllFLMSForAWell(int wellId, FluidLevelMeasurements fluidLevelMeasurement);

    public void deleteAllFLMSForAWell(int wellId);

    public void updateSpecificFLMS(int wellId, int flmId, FluidLevelMeasurements fluidLevelMeasurement);

    public void deleteSpecificFLMS(int wellId, int flmId);
}
