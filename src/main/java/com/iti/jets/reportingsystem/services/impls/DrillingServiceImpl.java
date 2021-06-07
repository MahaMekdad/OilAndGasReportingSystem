package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.DrillingInfo;
import com.iti.jets.reportingsystem.entities.ProductionBudget;
import com.iti.jets.reportingsystem.models.DrillingInfoModel;
import com.iti.jets.reportingsystem.repos.DrillingInfoRepository;
import com.iti.jets.reportingsystem.services.DrillingInfoService;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
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
        Type listType = new TypeToken<List<DrillingInfoModel>>(){}.getType();
        drillingInfoModelList=

//
//        List<ProductionBudget> productionBudget = new ArrayList<>();
//        productionBudget = productionBudgetRepository.findAll();
//        System.out.println("productionBudgetRepository.findAll()============ " + productionBudget);
//        List<ProductionBudgetModel> productionBudgetModels = new ArrayList<>();
//        Type listType = new TypeToken<List<ProductionBudgetModel>>(){}.getType();
//        productionBudgetModels = modelMapper.map(productionBudget,listType);
//        System.out.println("Production budgetData================ "+productionBudgetModels);
//        return productionBudgetModels;
        return drillingInfoModelList;
    }

}
