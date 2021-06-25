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

    List<FluidLevelMeasurements> findAllByDateGreaterThanEqualAndDateLessThanEqual(Date beginDate, Date endDate);

//    List<FluidLevelMeasurements> findAllByDateGreaterThanEqualAndDateLessThanEqual(Pageable pageable);

//    List<FluidLevelMeasurements> findAllByWell_WellIdEquals(int wellId, Pageable pageable);

    List<FluidLevelMeasurements> findAllByWell_WellIdEquals(int wellId);

    FluidLevelMeasurements findByWell_WellIdEqualsAndIdEquals(int wellId, int flmId);

    void removeFluidLevelMeasurementsByWell_WellIdEqualsAndIdEquals(int wellId, int flmId);

    @Transactional
    void removeByWell_WellIdEqualsAndIdEquals(int wellId, int flmId);

    List<FluidLevelMeasurements> findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(int wellId, Date beginDate, Date endDate, Pageable pageable);

    List<FluidLevelMeasurements> findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(int wellId, Date beginDate, Date endDate);

    List<FluidLevelMeasurements> findAllByWell_WellIdEquals(int wellId, Pageable pageable);

    @Query(value = "SELECT distinct year(date) as years FROM fluid_level_measurements", nativeQuery = true)
    List<Integer> findAllYears();

    @Query("select flm from FluidLevelMeasurements flm where flm.well.wellId = ?1")
    List<FluidLevelMeasurements> getAllForWellId(int wellId);
//
//    @Query("select sum(fluid_level) as fluidLevel, month(date) as year from fluid_level_measurements where well_id = ?1 group by year", nativeQuery = true)
//    List<FluidLevelMeasurements> getTotalFluidLevelByYear(Integer );
//
//    @Query("select sum(fluid_level) as fluidLevel, month(date) as year from fluid_level_measurements where year(date) > ?1 and year(date) < ?2 group by year", nativeQuery = true)
//    List<FluidLevelMeasurements> getTotalFluidLevelForASpecificYearRange(Integer yr1, Integer yr2);
//
//    @Query("select avg(fluid_level) as fluidLevel, month(date) as year from fluid_level_measurements group by year", nativeQuery = true)
//    List<FluidLevelMeasurements> getAverageFluidLevelByYear();
//
//    @Query("select avg(fluid_level) as fluidLevel, month(date) as year from fluid_level_measurements where year(date) > ?1 and year(date) < ?2 group by year", nativeQuery = true)
//    List<FluidLevelMeasurements> getAverageFluidLevelForASpecificYearRange(Integer yr1, Integer yr2);


}
