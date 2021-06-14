package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.WellTestRequest;
import com.iti.jets.openapi.model.WellTestResponse;

import java.util.List;

public interface WellTestDataService {

    //get a list of all available test records for all wells
    public List<WellTestResponse> getAllTestsForWells();

    //get list of tests for certain well
    public List<WellTestResponse> getAllTestsForAWell(int wellId);

    //add new record to the database
    public WellTestResponse insert(int wellId,WellTestRequest wellTestRequest);

    //update specific Test record for a specific well
    public WellTestResponse updateSpecificTest(int wellId, int recordId , WellTestRequest wellTestRequest);

    //delete s specific test Record
    public void deleteTestRecordByWellIdAndRecordId(int wellId,int testId);

}
