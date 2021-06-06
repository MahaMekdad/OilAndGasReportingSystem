package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.ProductionGeneralInfo;
import com.iti.jets.reportingsystem.models.ProductionGeneralInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionGeneralInfoRepository extends JpaRepository<ProductionGeneralInfo, Integer> {
}
