package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.IntervalsInfo;
import com.iti.jets.reportingsystem.entities.Well;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervalsInfoRepository extends JpaRepository<IntervalsInfo,Integer>{

 List<IntervalsInfo> findIntervalsInfoByWellIs(Well well);
}
