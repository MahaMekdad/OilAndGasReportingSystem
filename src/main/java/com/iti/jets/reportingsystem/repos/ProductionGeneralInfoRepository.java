package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductionGeneralInfoRepository extends JpaRepository<ProductionGeneralInfo, Integer> {

    public List<ProductionGeneralInfo> findAllByWell_WellIdEquals(int wellId);

    public ProductionGeneralInfo findByWell_WellIdEqualsAndIdEquals(int wellId, int pgiId);

    public void removeFluidLevelMeasurementsByWell_WellIdEqualsAndIdEquals(int wellId, int pgiId);

    @Transactional
    public void removeByWell_WellIdEqualsAndIdEquals(int wellId, int pgiId);

}
