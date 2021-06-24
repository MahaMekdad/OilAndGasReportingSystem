package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface FluidLevelMeasurementsRepository extends JpaRepository<FluidLevelMeasurements, Integer> {

    List<FluidLevelMeasurements> findAllByDateGreaterThanEqualAndDateLessThanEqual(Date beginDate, Date endDate, Pageable pageable);

    List<FluidLevelMeasurements> findAllByWell_WellIdEquals(int wellId, Pageable pageable);

    FluidLevelMeasurements findByWell_WellIdEqualsAndIdEquals(int wellId, int flmId);

    void removeFluidLevelMeasurementsByWell_WellIdEqualsAndIdEquals(int wellId, int flmId);

    @Transactional
    void removeByWell_WellIdEqualsAndIdEquals(int wellId, int flmId);

    List<FluidLevelMeasurements> findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(int wellId, Date beginDate, Date endDate, Pageable pageable);

    @Query("select flm from FluidLevelMeasurements flm where flm.well.wellId = ?1")
    List<FluidLevelMeasurements> getAllForWellId(int wellId);

//    List<FluidLevelMeasurements> findAll(Pageable pageable);

}
