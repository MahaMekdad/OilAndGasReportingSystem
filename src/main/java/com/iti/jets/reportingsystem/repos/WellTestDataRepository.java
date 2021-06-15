package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.WellTestData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WellTestDataRepository extends JpaRepository<WellTestData, Integer> {

    //retrieving all test Records related to specific well.
    @Query("SELECT wellTestRecord FROM WellTestData wellTestRecord WHERE wellTestRecord.well.wellId = :wellId")
    public List<WellTestData> findAllByWellId(@Param("wellId") Integer wellId);

    //find specific test record based on well id and record id

    @Query("SELECT wellTestRecord FROM WellTestData wellTestRecord WHERE wellTestRecord.well.wellId = :wellId and wellTestRecord.id = :recordId")
    Optional<WellTestData> findByWellIdAndTestId(@Param("wellId") Integer wellId, @Param("recordId") Integer recordId);

    //delete an existing test record based on  well record and the test Record
    @Modifying
    @Transactional
    @Query("delete FROM WellTestData wellTestRecord WHERE wellTestRecord.well.wellId = :wellId and wellTestRecord.id = :recordId")
    void deleteByWellIdAndRecordId(@Param("wellId") Integer wellId, @Param("recordId") Integer recordId);

    //get records based on date
    List<WellTestData> findAllByProductionDateGreaterThanEqualAndProductionDateLessThanEqual(Date beginDate, Date endDate);

    List<WellTestData> findAllByWell_WellIdEqualsAndIdEquals(int wellId, int testId);

   @Transactional
    void removeByWell_WellIdEqualsAndIdEquals(int wellId, int testId);

}
