package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.LabMesurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface LabMeasurementRepository extends JpaRepository<LabMesurement , Integer> {
    @Modifying
    @Transactional
    @Query(value="delete from LabMesurement l where l.well.wellId =?1 And l.id = ?2")
    void deleteByWellIdAndLabId(Integer wellId , Integer id);

    //    @Transactional
//    @Modifying
//    Long deleteByIdAndWell_WellIdEquals(Integer id , Integer wellId);
    List<LabMesurement> findAllByWell_WellIdEquals(Integer id);
    List<LabMesurement> findAllByDateGreaterThanEqualAndDateLessThanEqual(Date beginDate, Date endDate);
    List<LabMesurement> findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(int wellId, Date beginDate, Date endDate);




}
