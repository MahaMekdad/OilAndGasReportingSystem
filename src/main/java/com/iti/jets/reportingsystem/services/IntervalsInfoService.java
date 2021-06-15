package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.IntervalsInfoRequest;
import com.iti.jets.openapi.model.IntervalsInfoResponse;
import com.iti.jets.reportingsystem.entities.IntervalsInfo;
import com.iti.jets.reportingsystem.entities.Well;

import java.util.ArrayList;
import java.util.List;

public interface IntervalsInfoService {

    public List<IntervalsInfoResponse> getAllIntervalsInfo();

    public IntervalsInfoResponse getIntervalsInfoById(int id);

    public IntervalsInfo saveIntervalsInfo(IntervalsInfoRequest intervalsInfoRequest);

    public boolean updateIntervalsInfo(int id,IntervalsInfoRequest intervalsInfoRequest);

    public boolean deleteIntervalsInfo(int id);

}
