package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.*;
import com.iti.jets.reportingsystem.entities.Field;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.repos.FieldRepository;
import com.iti.jets.reportingsystem.services.FieldService;
import com.iti.jets.reportingsystem.utils.mappers.FieldMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FieldServiceImpl implements FieldService {

    private FieldRepository fieldRepository;
    private ModelMapper modelMapper;
//    private FieldMapper fieldMapper;


    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository, ModelMapper modelMapper) {
        this.fieldRepository = fieldRepository;
        this.modelMapper = modelMapper;
//        this.fieldMapper = fieldMapper;
    }

    @Override
    public void insert(FieldRequest fieldRequest) {

        Field field  = modelMapper.map(fieldRequest,Field.class);
        fieldRepository.saveAndFlush(field);

    }

    @Override
    public void update(int i, FieldRequest fieldRequest) {

    }

    @Override
    public List<FieldResponse> getAllFields() {
        List<Field> fieldEntities = fieldRepository.findAll();
        List<FieldResponse> fieldResponses = fieldEntities.
                stream().map(field-> modelMapper.map(field,FieldResponse.class))
                .collect(Collectors.toList());
        return fieldResponses;
    }

    @Override
    public FieldResponse getFieldByID(Integer fieldId) {
        FieldResponse fieldResponse = modelMapper.map(fieldRepository.findById(fieldId).get(),FieldResponse.class);

        return fieldResponse;
    }


    @Override
    public boolean delete(Integer fieldId) {
        FieldResponse fieldResponse ;
        fieldResponse= (FieldResponse) getFieldByID(fieldId);

        if(fieldResponse != null) {
            fieldRepository.deleteById(fieldId);
            return true;
        }
        else {
            return false;
        }

    }
}
