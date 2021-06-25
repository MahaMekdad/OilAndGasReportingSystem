package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.ConcessionsFieldsResponse;
import com.iti.jets.openapi.model.FieldsWellsResponse;
import com.iti.jets.openapi.model.ProductionBudegetDataResponse;
import com.iti.jets.reportingsystem.entities.Field;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.repos.*;
import com.iti.jets.reportingsystem.services.FieldsAndWellsService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class FieldsAndWellsImpl implements FieldsAndWellsService {
    private final FieldRepository fieldRepository;
    private final WellRepository wellRepository;
    private final ConcessionRepository concessionRepository;
    private ModelMapper modelMapper;

    @Autowired
    public FieldsAndWellsImpl(FieldRepository fieldRepository, WellRepository wellRepository, ConcessionRepository concessionRepository) {
        this.fieldRepository = fieldRepository;
        this.wellRepository = wellRepository;
        this.concessionRepository = concessionRepository;
    }

    @Override
    public List<ConcessionsFieldsResponse> getAllFieldsForConcession(int concessionId) {
        if (!concessionRepository.findById(concessionId).isPresent()) {
            throw new ResourceNotFoundException("There is concession with the given id");
        }
        List<Field> fields = fieldRepository.findAllByConcession_ConcessionIdEquals(concessionId);
        ConcessionsFieldsResponse concessionsFieldsResponse = new ConcessionsFieldsResponse();
        List<ConcessionsFieldsResponse> concessionsFieldsResponseList = new ArrayList<>();
        for(int i = 0 ; i <fields.size() ; i ++)
        {
            concessionsFieldsResponse.setFieldId(fields.get(i).getFieldId());
            concessionsFieldsResponse.setFieldCode(fields.get(i).getFieldCode());
            concessionsFieldsResponse.setFieldName(fields.get(i).getFieldName());
            concessionsFieldsResponseList.add(concessionsFieldsResponse);
            concessionsFieldsResponse = new ConcessionsFieldsResponse();

        }
        if (concessionsFieldsResponseList == null) {
            throw new ResourceNotFoundException("There is no Fields for this concession");
        } else
            return concessionsFieldsResponseList;
    }

    @Override
    public List<FieldsWellsResponse> getAllWellsForField(int fieldId) {
        if (!fieldRepository.findById(fieldId).isPresent()) {
            throw new ResourceNotFoundException("There is no Field with the given id");
        }
        List<Well> wells = wellRepository.findAllByField_FieldIdEquals(fieldId);
        List<FieldsWellsResponse> fieldsWellsResponseList = new ArrayList<>();
        FieldsWellsResponse fieldsWellsResponse = new FieldsWellsResponse();
        for(int i = 0; i <wells.size() ; i++)
        {
            fieldsWellsResponse.setWellId(wells.get(i).getWellId());
            fieldsWellsResponse.setWellCode(wells.get(i).getWellCode());
            fieldsWellsResponse.setWellName(wells.get(i).getWellName());
            fieldsWellsResponseList.add(fieldsWellsResponse);
            fieldsWellsResponse = new FieldsWellsResponse();

        }
        if (fieldsWellsResponseList == null) {
            throw new ResourceNotFoundException("There is no Wells for this field");
        }
        return fieldsWellsResponseList;
    }
}
