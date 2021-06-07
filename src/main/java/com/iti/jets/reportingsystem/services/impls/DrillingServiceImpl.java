package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.DrillingInfo;
import com.iti.jets.reportingsystem.models.DrillingInfoModel;
import com.iti.jets.reportingsystem.repos.DrillingInfoRepository;
import com.iti.jets.reportingsystem.services.DrillingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DrillingServiceImpl implements DrillingInfoService {
    @Autowired
    private DrillingInfoRepository drillingInfoRepository;
    @Override
    public List<DrillingInfoModel> getAllDrillingInfo() {

        List<DrillingInfoModel> drillingInfoModelList = new ArrayList<>();
        List<DrillingInfo> drillingInfooo = drillingInfoRepository.findAll();

//        drillingInfoModelList =
        return drillingInfoModelList;
    }

}
