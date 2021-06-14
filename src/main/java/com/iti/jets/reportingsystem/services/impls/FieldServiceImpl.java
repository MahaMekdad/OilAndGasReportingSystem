package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.*;
import com.iti.jets.reportingsystem.entities.Field;
import com.iti.jets.reportingsystem.repos.DummyCon;
import com.iti.jets.reportingsystem.repos.FieldRepository;
import com.iti.jets.reportingsystem.services.FieldService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final ModelMapper modelMapper;
    private final DummyCon concessionRepo;



    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository, ModelMapper modelMapper, DummyCon concessionRepo) {
        this.fieldRepository = fieldRepository;
        this.modelMapper = modelMapper;
        this.concessionRepo = concessionRepo;
    }

    @Override
    public void insert(FieldRequest fieldRequest) {
        List<Field> numOfFields = fieldRepository.findAllByConcession_ConcessionIdEquals(fieldRequest.getConcessionId().intValue());
        Field toBeInserted = new Field();
        toBeInserted.setFieldName(fieldRequest.getFieldName());
        toBeInserted.setConcession(concessionRepo.findById(fieldRequest.getConcessionId().intValue()).get());
        toBeInserted.setFieldCode("C"+fieldRequest.getConcessionId().intValue()+"F"+(numOfFields.size()+1));
        fieldRepository.save(toBeInserted);
    }



    @Override
    public FieldResponse update(int fieldId, FieldRequest fieldRequest) {
        Optional<Field> fieldById = fieldRepository.findById(fieldId);
        if (fieldById.isPresent()){
            Field field = fieldById.get();
            field.setFieldName(fieldRequest.getFieldName());
            Field savedEntity = fieldRepository.save(field);
            FieldResponse fieldResponse = modelMapper.map(savedEntity,FieldResponse.class);
            return fieldResponse;
        }else{
            throw new EntityNotFoundException("field with id: " + fieldId + " was not found");
        }
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
