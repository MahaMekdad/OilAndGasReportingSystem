package com.iti.jets.reportingsystem.utils.mappers;

import com.iti.jets.openapi.model.WellDailyActionsRequest;
import com.iti.jets.reportingsystem.entities.DailyActions;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class DailyActionsMapper {
    public DailyActions dailyActionsMap(WellDailyActionsRequest wellDailyActionsRequest) {
        DailyActions dailyActions = new DailyActions();
        dailyActions.setDate(Date.from(wellDailyActionsRequest.getDate().toInstant()));
        dailyActions.setActionDescription(wellDailyActionsRequest.getActionDescription());
        dailyActions.setLosses(wellDailyActionsRequest.getLosses().doubleValue());
        dailyActions.setDownTime(wellDailyActionsRequest.getDownTime().floatValue());
        dailyActions.setNetProduction(wellDailyActionsRequest.getNetProduction().doubleValue());
        return dailyActions;
    }

}
