package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.AllFluidLevelMeasurementResponse;
import com.iti.jets.openapi.model.FluidLevelMeasurementRequest;
import com.iti.jets.openapi.model.FluidLevelMeasurementResponse;
import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface FluidLevelMeasurementsService {

    void delete(int flmId);

//    void insert(FluidLevelMeasurementsModel fluidLevelMeasurementModel);
    void insert(FluidLevelMeasurementRequest fluidLevelMeasurementRequest, int WellId);

//    List<FluidLevelMeasurementsModel> getAllFLMS();
    List<AllFluidLevelMeasurementResponse> getAllFLMS();

//    List<FluidLevelMeasurementsModel> getAllFLMS(Date beginDate, Date endDate);
    List<AllFluidLevelMeasurementResponse> getAllFLMSWithDateAndPaging(Date beginDate, Date endDate, Integer pageNum, Integer elementNum);

    List<AllFluidLevelMeasurementResponse> getAllFLMSWithPaging(Integer pageNum, Integer elementNum);

    List<AllFluidLevelMeasurementResponse> getAllFLMSWithDate(Date beginDate, Date endDate);

//    List<FluidLevelMeasurementsModel> getAllFLMSForAWell(int wellId);
    List<FluidLevelMeasurementResponse> getAllFLMSForAWell(int wellId);

//    List<FluidLevelMeasurementsModel> getAllFLMSForAWell(int wellId, Date beginDate, Date endDate);
    List<FluidLevelMeasurementResponse> getAllFLMSForAWellWithDateAndPaging(int wellId, Date beginDate, Date endDate, Integer pageNum, Integer elementNum);

    List<FluidLevelMeasurementResponse> getAllFLMSForAWellWithDate(int wellId, Date beginDate, Date endDate);

    List<FluidLevelMeasurementResponse> getAllFLMSForAWellWithPaging(int wellId, Integer pageNum, Integer elementNum);

//    public void updateAllFLMSForAWell(int wellId, FluidLevelMeasurements fluidLevelMeasurement);
//
//    public void deleteAllFLMSForAWell(int wellId);

//    void updateSpecificFLMS(int wellId, int flmId, FluidLevelMeasurementsModel fluidLevelMeasurementModel);
    void updateSpecificFLMS(int wellId, int flmId, FluidLevelMeasurementRequest fluidLevelMeasurementRequest);

    void deleteSpecificFLMS(int wellId, int flmId);

//    List<FluidLevelMeasurementPerYearResponse> getTotalFluidLevelByYear();
//
//    List<FluidLevelMeasurementPerYearResponse> getTotalFluidLevelForASpecificYearRange(Integer yr1, Integer yr2);
//
//    List<FluidLevelMeasurementPerYearResponse> getAverageFluidLevelByYear();
//
//    List<FluidLevelMeasurementPerYearResponse> getAverageFluidLevelForASpecificYearRange(Integer yr1, Integer yr2);
}
