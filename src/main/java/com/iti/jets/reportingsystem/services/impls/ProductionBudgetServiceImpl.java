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
        Type listType = new TypeToken<List<ProductionBudgetModel>>() {
        }.getType();
        productionBudgetModels = modelMapper.map(productionBudget, listType);
        System.out.println("Production budgetData================ " + productionBudgetModels);
        return productionBudgetModels;

    }

    @Override
    public void create(ProductionBudgetModel productionBudgetModel) {
        ProductionBudget productionBudget = new ProductionBudget();
        productionBudget = modelMapper.map(productionBudgetModel, ProductionBudget.class);
        productionBudgetRepository.saveAndFlush(productionBudget);
    }

    @Override
    public void delete(int id) {
        productionBudgetRepository.deleteById(id);

    }

    @Override
    public ProductionBudgetModel findProductionBudgetByProductionDate(Date date) {
        System.out.println("Production_date======= " + date);
        ProductionBudget productionBudget = productionBudgetRepository.findByProductionDateEquals(date);
        System.out.println("productionBudget Date object ===== " + productionBudget);
        ProductionBudgetModel productionBudgetModel = new ProductionBudgetModel();
        productionBudgetModel = modelMapper.map(productionBudget, ProductionBudgetModel.class);
        System.out.println("productionbudgetModel====== " + productionBudgetModel);
        return productionBudgetModel;
    }

    @Override
    public ProductionBudgetModel findProductionBudgetById(int id) {
        System.out.println("id = " + id);
        ProductionBudget productionBudget = productionBudgetRepository.findProductionBudgetById(id);
        System.out.println("productionBudget Date object ===== " + productionBudget);
        ProductionBudgetModel productionBudgetModel = new ProductionBudgetModel();
        productionBudgetModel = modelMapper.map(productionBudget, ProductionBudgetModel.class);
        System.out.println("productionbudgetModel====== " + productionBudgetModel);
        return productionBudgetModel;
    }

    @Override
    public void updateProductionBudget(int id, ProductionBudgetModel productionBudgetModel) {
        ProductionBudget productionBudget = productionBudgetRepository.findById(id).get();
        if (productionBudgetModel.getProductionDate() != null) {
            productionBudget.setProductionDate(productionBudgetModel.getProductionDate());
        }
        if (productionBudgetModel.getMeleiha() != null) {
            productionBudget.setMeleiha(productionBudgetModel.getMeleiha());
        }
        if (productionBudgetModel.getAghar() != null) {
            productionBudget.setAghar(productionBudgetModel.getAghar());
        }
        if (productionBudgetModel.getEastKanays() != null) {
            productionBudget.setEastKanays(productionBudgetModel.getEastKanays());
        }
        if (productionBudgetModel.getZarif() != null) {
            productionBudget.setZarif(productionBudgetModel.getZarif());
        }
        if (productionBudgetModel.getRaml() != null) {
            productionBudget.setRaml(productionBudgetModel.getRaml());
        }
        if (productionBudgetModel.getFaras() != null) {
            productionBudget.setFaras(productionBudgetModel.getFaras());
        }
        if (productionBudgetModel.getWesternDesert() != null) {
            productionBudget.setWesternDesert(productionBudgetModel.getWesternDesert());
        }
        if (productionBudgetModel.getAshrafi() != null) {
            productionBudget.setAshrafi(productionBudgetModel.getAshrafi());
        }
        if (productionBudgetModel.getAgibaOil() != null) {
            productionBudget.setAgibaOil(productionBudgetModel.getAgibaOil());
        }
        if (productionBudgetModel.getSalesGas() != null) {
            productionBudget.setSalesGas(productionBudgetModel.getSalesGas());
        }
        if (productionBudgetModel.getAgibaBoe() != null) {
            productionBudget.setAgibaBoe(productionBudgetModel.getAgibaBoe());
        }
        productionBudgetRepository.saveAndFlush(productionBudget);
    }
}
