package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.DailyActions;
import com.iti.jets.reportingsystem.entities.LabMesurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface DailyActionsRepository extends JpaRepository<DailyActions, Integer> {

    @Modifying
    @Transactional
    @Query(value="delete from DailyActions l where l.well.wellId =?1 And l.id = ?2")
    void deleteByWellIdAndDailyActionId(Integer wellId , Integer id);

    @Query(value = "SELECT ppc FROM DailyActions ppc WHERE ppc.shutinTypeLevel4.id = ?1")
    List<DailyActions> findByShLvl4(Integer shLvl4);

    @Query(value = "SELECT ppc FROM DailyActions ppc WHERE ppc.losses = ?1")
    List<DailyActions> findByLosses(Double losses);

    @Query(value = "SELECT ppc FROM DailyActions ppc WHERE ppc.downTime = ?1")
    List<DailyActions> findByDownTime(Float downTime);

    List<DailyActions> findAllByWell_WellIdEquals(Integer id);
    List<DailyActions> findAllByShutinTypeLevel4(Integer id);
    List<DailyActions> findAllByDateGreaterThanEqualAndDateLessThanEqual(Date beginDate, Date endDate);
    List<DailyActions> findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(int wellId, Date beginDate, Date endDate);
    List<DailyActions> findAllByWell_WellIdEqualsAndShutinTypeLevel4Equals(int wellId, int shLvl4);
    List<DailyActions> findAllByWell_WellIdEqualsAndLossesEquals(int wellId, Double losses);
    List<DailyActions> findAllByWell_WellIdEqualsAndDownTimeEquals(int wellId, Float downTime);
}
