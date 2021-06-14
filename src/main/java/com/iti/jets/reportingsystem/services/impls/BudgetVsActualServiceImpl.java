package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.FieldsBudgetAndActualRequest;
import com.iti.jets.openapi.model.FieldsBudgetAndActualResponse;
import com.iti.jets.reportingsystem.entities.BudgetActual;
import com.iti.jets.reportingsystem.repos.BudgetVsActualRepository;
import com.iti.jets.reportingsystem.services.BudgetVsActualService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
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

//                budgetActualList.
//                stream().map(budgetRecord -> modelMapper.map(budgetRecord, FieldsBudgetAndActualResponse.class))
//                .collect(Collectors.toList());


        Type listType = new TypeToken<List<FieldsBudgetAndActualResponse>>() {
        }.getType();
        responseList = modelMapper.map(budgetActualList, listType);
//            for (int i = 0; i < budgetActualList.size(); i++) {
//                budgetActualList.get(i).setFieldId(wellEntities.get(i).getField().getFieldId());


        return responseList;

    }

    @Override
    public List<FieldsBudgetAndActualResponse> findAllBudgetsByDate() {
        return null;
    }

    @Override
    public FieldsBudgetAndActualResponse addBudgetRecord(FieldsBudgetAndActualRequest requestBody) {
        BudgetActual entity = modelMapper.map(requestBody, BudgetActual.class);
        budgetVsActualRepository.save(entity);
        return modelMapper.map(requestBody, FieldsBudgetAndActualResponse.class);
    }

    @Override
    public FieldsBudgetAndActualResponse updateBudgetRecord(int recordId, FieldsBudgetAndActualRequest requestBody) {
        Optional<BudgetActual> findOptional = budgetVsActualRepository.findById(recordId);
        if (findOptional.isPresent()) {
            BudgetActual entity = modelMapper.map(requestBody, BudgetActual.class);
            budgetVsActualRepository.save(entity);
        } else {
            throw new EntityNotFoundException();
        }
        return modelMapper.map(requestBody, FieldsBudgetAndActualResponse.class);
    }

    @Override
    public void deleteRecord(int recordId) {
        Optional<BudgetActual> findOptional = budgetVsActualRepository.findById(recordId);
        if (findOptional.isPresent()) {
            budgetVsActualRepository.deleteById(recordId);
        } else {
            throw new EntityNotFoundException();
        }

    }
}
