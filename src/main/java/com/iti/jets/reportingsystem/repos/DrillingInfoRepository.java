package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.DrillingInfo;
import com.iti.jets.reportingsystem.models.DrillingInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrillingInfoRepository extends JpaRepository<DrillingInfo, Integer > {

}
