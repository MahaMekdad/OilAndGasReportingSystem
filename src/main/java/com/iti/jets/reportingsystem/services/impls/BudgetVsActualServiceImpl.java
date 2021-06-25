package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.FieldsBudgetAndActualRequest;
import com.iti.jets.openapi.model.FieldsBudgetAndActualResponse;
import com.iti.jets.reportingsystem.entities.BudgetActual;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.repos.BudgetVsActualRepository;
import com.iti.jets.reportingsystem.services.BudgetVsActualService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetVsActualServiceImpl implements BudgetVsActualService {
    private BudgetVsActualRepository budgetVsActualRepository;
    private ModelMapper modelMapper;

    @Autowired
    public BudgetVsActualServiceImpl(BudgetVsActualRepository budgetVsActualRepository, ModelMapper modelMapper) {
        this.budgetVsActualRepository = budgetVsActualRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }

    @Override
    public List<FieldsBudgetAndActualResponse> findAll() {
        List<BudgetActual> budgetActualList = budgetVsActualRepository.findAll();

        List<FieldsBudgetAndActualResponse> responseList = new ArrayList<>();
        Type listType = new TypeToken<List<FieldsBudgetAndActualResponse>>() {
        }.getType();
        responseList = modelMapper.map(budgetActualList, listType);
        for (int i = 0; i < budgetActualList.size(); i++) {
            responseList.get(i).setRecordId(BigDecimal.valueOf(budgetActualList.get(i).getId()));
            System.out.println(responseList.get(i).getRecordId());
        }
        return responseList;
    }

    @Override
    public List<FieldsBudgetAndActualResponse> findAllBudgetsByDate() {
        return null;
    }

    @Override
    public FieldsBudgetAndActualResponse addBudgetRecord(FieldsBudgetAndActualRequest requestBody) {
        BudgetActual entity = modelMapper.map(requestBody, BudgetActual.class);
        entity.setMeleihaPercentage((entity.getMeleihaActual()/entity.getMeleihaBudget())*100);
        entity.setAgharPercentage((entity.getAgharActual()/entity.getAgharBudget())*100);
        entity.setEastKanysPercentage((entity.getEastKanaysActual()/entity.getEastKanysPercentage())*100);
        entity.setZarifPercentage((entity.getZarifActual()/entity.getZarifBudget())*100);
        entity.setFarasPercentage((entity.getFarasActual()/entity.getFarasBudget())*100);
        entity.setRamlPercentage((entity.getRamlActual()/entity.getRamlBudget())*100);
        entity.setWesternDesertPercentage((entity.getWesternDesertActual()/entity.getWesternDesertBudget())*100);
        entity.setAshrafiPercentage((entity.getAshrafiActual()/entity.getAshrafiBudget())*100);
        System.out.println(entity.getAshrafiPercentage() + "----->");
        entity.setAgibaOilPercentage((entity.getAgibaOilActual()/entity.getAgibOilBudget())*100);
        budgetVsActualRepository.save(entity);
        return modelMapper.map(requestBody, FieldsBudgetAndActualResponse.class);
    }

    @Override
    public FieldsBudgetAndActualResponse updateBudgetRecord(int recordId, FieldsBudgetAndActualRequest requestBody) {
        if(!budgetVsActualRepository.findById(recordId).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with this id");
        }
        Optional<BudgetActual> findOptional = budgetVsActualRepository.findById(recordId);
        System.out.println("1:the passed id is: " + recordId);
        if (findOptional.isPresent()) {
            System.out.println(" 2: the passed id is: " + recordId);
            BudgetActual entity = modelMapper.map(requestBody, BudgetActual.class);
            entity.setId(recordId);
            budgetVsActualRepository.save(entity);
        } else {
            throw new EntityNotFoundException();
        }
        return modelMapper.map(requestBody, FieldsBudgetAndActualResponse.class);
    }

    @Override
    public void deleteRecord(int recordId) {
        if(!budgetVsActualRepository.findById(recordId).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with this id");
        }
        Optional<BudgetActual> findOptional = budgetVsActualRepository.findById(recordId);
        if (findOptional.isPresent()) {
            budgetVsActualRepository.deleteById(recordId);
        } else {
            throw new EntityNotFoundException();
        }

    }
}
