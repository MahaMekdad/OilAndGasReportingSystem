package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.FieldsBudgetAndActualRequest;
import com.iti.jets.openapi.model.FieldsBudgetAndActualResponse;

import java.util.List;

public interface BudgetVsActualService {
    public List<FieldsBudgetAndActualResponse> findAll();
    public List<FieldsBudgetAndActualResponse> findAllBudgetsByDate();
    public FieldsBudgetAndActualResponse addBudgetRecord(FieldsBudgetAndActualRequest requestBody);
    public FieldsBudgetAndActualResponse updateBudgetRecord(int recordId,FieldsBudgetAndActualRequest requestBody);
    public void deleteRecord(int recordId);
}
