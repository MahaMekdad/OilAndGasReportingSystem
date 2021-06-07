package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.ProductionBudget;
import com.iti.jets.reportingsystem.models.ProductionBudgetModel;
import com.iti.jets.reportingsystem.repos.ProductionBudgetRepository;
import com.iti.jets.reportingsystem.services.ProductionBudgetService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class ProductionBudgetServiceImpl implements ProductionBudgetService {
    @Autowired
    private ProductionBudgetRepository productionBudgetRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<ProductionBudgetModel> getGetAllProductionBudget() {
        List<ProductionBudget> productionBudget = new ArrayList<>();
        productionBudget = productionBudgetRepository.findAll();
        System.out.println("productionBudgetRepository.findAll()============ " + productionBudget);
        List<ProductionBudgetModel> productionBudgetModels = new ArrayList<>();
        Type listType = new TypeToken<List<ProductionBudgetModel>>(){}.getType();
        productionBudgetModels = modelMapper.map(productionBudget,listType);
        System.out.println("Production budgetData================ "+productionBudgetModels);
        return productionBudgetModels;

    }

    @Override
    public void create(ProductionBudgetModel productionBudgetModel) {
        ProductionBudget productionBudget = new ProductionBudget();
        productionBudget = modelMapper.map(productionBudgetModel , ProductionBudget.class);
        productionBudgetRepository.saveAndFlush(productionBudget);
    }

    @Override
    public void delete(int id) {
        productionBudgetRepository.deleteById(id);

    }

    @Override
    public ProductionBudgetModel findProductionBudgetByProductionDate(String date) {
        System.out.println("Production_date======= "+date);
        ProductionBudget productionBudget = productionBudgetRepository.findProductionBudgetByProductionDate(date);
        System.out.println("productionBudget Date object ===== "+productionBudget);
        ProductionBudgetModel productionBudgetModel = new ProductionBudgetModel();
        productionBudgetModel = modelMapper.map(productionBudget,ProductionBudgetModel.class);
        System.out.println("productionbudgetModel====== "+productionBudgetModel);
        return productionBudgetModel;
    }

    @Override
    public ProductionBudgetModel findProductionBudgetById(int id) {
        System.out.println("id = " +id);
        ProductionBudget productionBudget = productionBudgetRepository.findProductionBudgetById(id);
        System.out.println("productionBudget Date object ===== "+productionBudget);
        ProductionBudgetModel productionBudgetModel = new ProductionBudgetModel();
        productionBudgetModel = modelMapper.map(productionBudget,ProductionBudgetModel.class);
        System.out.println("productionbudgetModel====== "+productionBudgetModel);
        return productionBudgetModel;
    }
}
