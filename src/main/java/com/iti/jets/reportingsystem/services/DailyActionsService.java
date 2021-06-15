package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.LabMeasurementRequest;
import com.iti.jets.openapi.model.LabMeasurementResponse;
import com.iti.jets.openapi.model.WellDailyActionsRequest;
import com.iti.jets.openapi.model.WellDailyActionsResponse;

import java.util.Date;
import java.util.List;

public interface DailyActionsService {

    void insert(Integer wellId , WellDailyActionsRequest wellDailyActionsRequest);

    void update(Integer wellId , Integer dailyActionId , WellDailyActionsRequest wellDailyActionsRequest);

    List<WellDailyActionsResponse> getAllDailyActions();

    List<WellDailyActionsResponse> getAllDailyActionsFromWell(Integer wellId);

    WellDailyActionsResponse getAdailyActionFromAwell(Integer wellId , Integer labId);

    List<WellDailyActionsResponse> getAllDailyActions (Date beginDate, Date endDate);

    List<WellDailyActionsResponse> getAllDailyActionsByShLvl4 (Long shlvl4);

    List<WellDailyActionsResponse> getAllDailyActionsByLosses(Double losses);

    List<WellDailyActionsResponse> getAllDailyActionsByDownTime (Float downTime);

    List<WellDailyActionsResponse> getAllDailyActionsFromWell(Integer wellId, Date beginDate, Date endDate);

    List<WellDailyActionsResponse> getAllDailyActionsFromWellWithShLvl4(Integer wellId , Long shlvl4);

    List<WellDailyActionsResponse> getAllDailyActionsFromWellWithLosses(Integer wellId , Double losses);

    List<WellDailyActionsResponse> getAllDailyActionsFromWellWithDownTime(Integer wellId , Float downTime);




    public boolean delete(Integer wellId , Integer labId);





}
