package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.IntervalsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervalsInfoRepository extends JpaRepository<IntervalsInfo,Integer>{


}
