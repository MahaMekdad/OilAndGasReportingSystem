package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.WellGeneralInfoRequest;
import com.iti.jets.openapi.model.WellGeneralInfoResponse;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.entities.WellGeneralInfo;
import com.iti.jets.reportingsystem.models.WellCoordinatesDto;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface WellGeneralInfoService {

    public List<WellGeneralInfoResponse> getAllWellsGeneralInfo();
    public WellGeneralInfoResponse getWellGeneralInfoById(int id);
    public WellGeneralInfo saveWellGeneralInfo(WellGeneralInfoRequest wellGeneralInfoRequest);
    public boolean updateWellGeneralInfo(int id,WellGeneralInfoRequest wellGeneralInfoRequest);
    public boolean deleteWellGeneralInfo(int id);
    public List<WellGeneralInfo> getWellsCoordinates(int fieldId);
}
