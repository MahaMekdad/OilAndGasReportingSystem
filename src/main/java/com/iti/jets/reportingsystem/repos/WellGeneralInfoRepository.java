package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.Field;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.entities.WellGeneralInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WellGeneralInfoRepository extends JpaRepository<WellGeneralInfo,Integer> {
    WellGeneralInfo findWellGeneralInfoByWellIs(Well well);

    @Query("from WellGeneralInfo wg , Well w where wg.well =w and w.field=:field")
    List<WellGeneralInfo> findWellsGeneralInfoByFieldId(Field field);
}
