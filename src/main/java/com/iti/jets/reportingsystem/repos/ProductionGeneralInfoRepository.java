package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ProductionGeneralInfoRepository extends JpaRepository<ProductionGeneralInfo, Integer> {

    List<ProductionGeneralInfo> findAllByInitialProdDateGreaterThanEqualAndInitialProdDateLessThanEqual(Date beginDate, Date endDate);

    List<ProductionGeneralInfo> findAllByWell_WellIdEquals(int wellId);

    ProductionGeneralInfo findByWell_WellIdEqualsAndIdEquals(int wellId, int pgiId);

    void removeFluidLevelMeasurementsByWell_WellIdEqualsAndIdEquals(int wellId, int pgiId);

    List<ProductionGeneralInfo> findAllByWell_WellIdEqualsAndInitialProdDateGreaterThanEqualAndInitialProdDateLessThanEqual(int wellId, Date beginDate, Date endDate);

    @Transactional
    void removeByWell_WellIdEqualsAndIdEquals(int wellId, int pgiId);

    List<ProductionGeneralInfo> findAllByWell_WellIdEqualsAndPowerSourceTypeEquals(int wellId, String powerSourceType);

    List<ProductionGeneralInfo> findAllByWell_WellIdEqualsAndProcessionPlantEquals(int wellId, String processionPlant);

    List<ProductionGeneralInfo> findAllByWell_WellIdEqualsAndCurrentWellTypeEquals(int wellId, String currentWellType);

    List<ProductionGeneralInfo> findAllByWell_WellIdEqualsAndCurrentLiftTypeEquals(int wellId, String currentLiftType);

    List<ProductionGeneralInfo> findAllByWell_WellIdEqualsAndCurrentStatusEquals(int wellId, String currentStatus);

}
