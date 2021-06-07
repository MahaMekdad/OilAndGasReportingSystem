package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FluidLevelMeasurementsRepository extends JpaRepository<FluidLevelMeasurements, Integer> {

    public List<FluidLevelMeasurements> findAllByWell_WellIdEquals(int wellId);

//    @Transactional
//    @Modifying
//    @Query("update UserEntity u set age =:age")
//    public void updateAllForWellId(@Param)
}
