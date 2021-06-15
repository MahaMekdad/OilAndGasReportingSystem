package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.ProductionBudegetDataResponse;
import com.iti.jets.openapi.model.ProductionBudegetRequest;
import com.iti.jets.reportingsystem.entities.ProductionBudget;
import com.iti.jets.reportingsystem.repos.ProductionBudgetRepository;
import com.iti.jets.reportingsystem.services.ProductionBudgetService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ProductionBudgetServiceImpl implements ProductionBudgetService {

    private final ProductionBudgetRepository productionBudgetRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductionBudgetServiceImpl(ProductionBudgetRepository productionBudgetRepository, ModelMapper modelMapper){
        this.productionBudgetRepository = productionBudgetRepository;
        this.modelMapper = modelMapper;
    }
    public OffsetDateTime dateHelper(Date dateToConvert){
        LocalDate localDate = new java.sql.Date(dateToConvert.getTime()).toLocalDate();
        return localDate.atTime(0,0,0).atOffset(ZoneOffset.UTC);
    }
    @Override
    public List<ProductionBudegetDataResponse> getGetAllProductionBudget() {
        List<ProductionBudget> productionBudget = new ArrayList<>();
        productionBudget = productionBudgetRepository.findAll();
        System.out.println("productionBudgetRepository.findAll()============ " + productionBudget.get(0).getProductionDate());
        List<ProductionBudegetDataResponse> productionBudgetModels = new ArrayList<>();
        Type listType = new TypeToken<List<ProductionBudegetDataResponse>>() {
        }.getType();
        productionBudgetModels = modelMapper.map(productionBudget, listType);
        for (int i = 0; i < productionBudget.size(); i++) {
            System.out.println("size = " +productionBudget.size());
            System.out.println("date =" +productionBudget.get(i).getProductionDate());
            OffsetDateTime offsetDateTime = dateHelper(productionBudget.get(i).getProductionDate());
            System.out.println("offseet == "+offsetDateTime);
            productionBudgetModels.get(i).setProductionDate(offsetDateTime);
       }


        System.out.println("Production budgetData================ " + productionBudgetModels.get(0).getProductionDate());
        return productionBudgetModels;

    }

    @Override
    public void create(ProductionBudegetRequest productionBudgetModel) {
        ProductionBudget productionBudget = new ProductionBudget();
        productionBudget = modelMapper.map(productionBudgetModel, ProductionBudget.class);
        productionBudget.setProductionDate(Date.from(productionBudgetModel.getProductionDate().toInstant()));
        productionBudgetRepository.saveAndFlush(productionBudget);
    }

    @Override
    public void delete(int id) {
        productionBudgetRepository.deleteById(id);

    }

    @Override
    public List<ProductionBudegetDataResponse> findProductionBudgetByProductionDate(Date date) {
        System.out.println("Production_date======= " + date);
        List<ProductionBudget> productionBudget = productionBudgetRepository.findByProductionDateEquals(date);
        System.out.println("productionBudget Date object ===== " + productionBudget);
        List<ProductionBudegetDataResponse> productionBudgetModel = new ArrayList<>();
        Type listType = new TypeToken<List<ProductionBudegetDataResponse>>() {
        }.getType();
        productionBudgetModel = modelMapper.map(productionBudget, listType);
        for(int i = 0 ; i <productionBudget.size() ; i++)
        {
            productionBudgetModel.get(i).setProductionDate(dateHelper(productionBudget.get(i).getProductionDate()));
        }
        System.out.println("productionbudgetModel====== " + productionBudgetModel);
        return productionBudgetModel;
    }

    @Override
    public ProductionBudegetDataResponse findProductionBudgetById(int id) {
        System.out.println("id = " + id);
        ProductionBudget productionBudget = productionBudgetRepository.findProductionBudgetById(id);
        System.out.println("productionBudget Date object ===== " + productionBudget);
        ProductionBudegetDataResponse productionBudgetModel = new ProductionBudegetDataResponse();
        productionBudgetModel = modelMapper.map(productionBudget, ProductionBudegetDataResponse.class);
        System.out.println("productionbudgetModel====== " + productionBudgetModel);
        return productionBudgetModel;
    }

    @Override
    public void updateProductionBudget(int id, ProductionBudegetRequest productionBudgetModel) {
        ProductionBudget productionBudget = productionBudgetRepository.findById(id).get();
        if (productionBudgetModel.getProductionDate() != null) {
            LocalDate localDate = productionBudgetModel.getProductionDate().toLocalDate();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            System.out.println(localDate);
            Date date1 = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            productionBudget.setProductionDate(date1);
        }
        if (productionBudgetModel.getMeleiha() != null) {
            productionBudget.setMeleiha(productionBudgetModel.getMeleiha().doubleValue());
        }
        if (productionBudgetModel.getAghar() != null) {
            productionBudget.setAghar(productionBudgetModel.getAghar().doubleValue());
        }
        if (productionBudgetModel.getEastKanays() != null) {
            productionBudget.setEastKanays(productionBudgetModel.getEastKanays().doubleValue());
        }
        if (productionBudgetModel.getZarif() != null) {
            productionBudget.setZarif(productionBudgetModel.getZarif().doubleValue());
        }
        if (productionBudgetModel.getRaml() != null) {
            productionBudget.setRaml(productionBudgetModel.getRaml().doubleValue());
        }
        if (productionBudgetModel.getFaras() != null) {
            productionBudget.setFaras(productionBudgetModel.getFaras().doubleValue());
        }
        if (productionBudgetModel.getWesternDesert() != null) {
            productionBudget.setWesternDesert(productionBudgetModel.getWesternDesert().doubleValue());
        }
        if (productionBudgetModel.getAshrafi() != null) {
            productionBudget.setAshrafi(productionBudgetModel.getAshrafi().doubleValue());
        }
        if (productionBudgetModel.getAgibaOil() != null) {
            productionBudget.setAgibaOil(productionBudgetModel.getAgibaOil().doubleValue());
        }
        if (productionBudgetModel.getSalesGas() != null) {
            productionBudget.setSalesGas(productionBudgetModel.getSalesGas().doubleValue());
        }
        if (productionBudgetModel.getAgibaBOE() != null) {
            productionBudget.setAgibaBoe(productionBudgetModel.getAgibaBOE().doubleValue());
        }
        productionBudgetRepository.saveAndFlush(productionBudget);
    }
}
