package com.iti.jets.reportingsystem.services;

import com.iti.jets.openapi.model.ConcessionsFieldsResponse;
import com.iti.jets.openapi.model.FieldsWellsResponse;

import java.util.List;

public interface FieldsAndWellsService {
    List<ConcessionsFieldsResponse> getAllFieldsForConcession(int concessionId);
    List<FieldsWellsResponse> getAllWellsForField(int fieldId);
}
