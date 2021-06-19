package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.entities.WellGeneralInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WellGeneralInfoRepository extends JpaRepository<WellGeneralInfo,Integer> {
    WellGeneralInfo findWellGeneralInfoByWellIs(Well well);
}
