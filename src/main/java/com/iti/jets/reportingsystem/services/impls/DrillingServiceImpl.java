package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.DrillingInfo;
import com.iti.jets.reportingsystem.entities.ProductionBudget;
import com.iti.jets.reportingsystem.models.DrillingInfoModel;
import com.iti.jets.reportingsystem.repos.DrillingInfoRepository;
import com.iti.jets.reportingsystem.services.DrillingInfoService;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<DrillingInfoModel> getAllDrillingInfo() {

        List<DrillingInfoModel> drillingInfoModelList = new ArrayList<>();
        List<DrillingInfo> drillingInfooo = drillingInfoRepository.findAll();
        Type listType = new TypeToken<List<DrillingInfoModel>>() {
        }.getType();
        drillingInfoModelList = modelMapper.map(drillingInfooo, listType);

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

    @Override
    public DrillingInfoModel getForWellId(int id) {
        DrillingInfo drillingInfo = drillingInfoRepository.getAllForWellId(id);
        System.out.println("drillingInfo====== " + drillingInfo);
        DrillingInfoModel drillingInfoModel = new DrillingInfoModel();
        drillingInfoModel = modelMapper.map(drillingInfo, DrillingInfoModel.class);
        return drillingInfoModel;
    }

    @Override
    public void creat(DrillingInfoModel drillingInfoModel) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(DrillingInfoModel drillingInfoModel) {

    }
}
