package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FluidLevelMeasurementsRepository extends JpaRepository<FluidLevelMeasurements, Integer> {

    public List<FluidLevelMeasurements> findAllByWell_WellIdEquals(int wellId);

    public FluidLevelMeasurements findByWell_WellIdEqualsAndIdEquals(int wellId, int flmId);

    public void removeFluidLevelMeasurementsByWell_WellIdEqualsAndIdEquals(int wellId, int flmId);

    @Transactional
    public void removeByWell_WellIdEqualsAndIdEquals(int wellId, int flmId);

    @Query("select flm from FluidLevelMeasurements flm where flm.well.wellId = ?1")
    public List<FluidLevelMeasurements> getAllForWellId(int wellId);

}
