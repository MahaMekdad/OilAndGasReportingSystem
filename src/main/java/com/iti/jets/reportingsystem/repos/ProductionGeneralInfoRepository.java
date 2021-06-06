package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;
import com.iti.jets.reportingsystem.models.ProductionGeneralInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionGeneralInfoRepository extends JpaRepository<ProductionGeneralInfo, Integer> {

    public List<ProductionGeneralInfo> findAllByWell_WellIdEquals(int wellId);
}
