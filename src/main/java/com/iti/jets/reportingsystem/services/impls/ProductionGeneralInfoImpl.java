package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.repos.ProductionGeneralInfoRepository;
import com.iti.jets.reportingsystem.services.ProductionGeneralInfoService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductionGeneralInfoImpl implements ProductionGeneralInfoService {

    @Autowired
    private ProductionGeneralInfoRepository pgiRepo;
}
