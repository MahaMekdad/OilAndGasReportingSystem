package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.WellRequest;
import com.iti.jets.openapi.model.WellResponse;
import com.iti.jets.reportingsystem.entities.Field;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.repos.FieldRepository;
import com.iti.jets.reportingsystem.repos.WellRepository;
import com.iti.jets.reportingsystem.services.WellService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WellServiceImpl implements WellService {

    private WellRepository wellRepository;
    private ModelMapper modelMapper;
    private FieldRepository fieldRepository;

    @Autowired
    public WellServiceImpl(WellRepository wellRepository, ModelMapper modelMapper, FieldRepository fieldRepository) {
        this.wellRepository = wellRepository;
        this.modelMapper = modelMapper;
        this.fieldRepository = fieldRepository;
    }

    @Override
    public void insert(WellRequest wellRequest) {
        List<Well> numOfWells = wellRepository.findAllByField_FieldIdEquals(wellRequest.getFieldId());
        Well well = new Well();
        Field field = fieldRepository.findById(wellRequest.getFieldId()).get();
        well.setField(field);
        well.setWellName(wellRequest.getWellName());
        well.setWellCode(field.getFieldCode()+"W"+(numOfWells.size()+1));
        wellRepository.saveAndFlush(well);
    }


    //done
    @Override
    public WellResponse updateWell(int wellId, WellRequest wellRequest) {
        Optional<Well> wellById = wellRepository.findById(wellId);
        if (wellById.isPresent()) {
            Well well = wellById.get();
            well.setWellName(wellRequest.getWellName());
            Well savedEntity = wellRepository.save(well);
            WellResponse wellResponse = modelMapper.map(savedEntity, WellResponse.class);
            return wellResponse;
        } else {
            throw new EntityNotFoundException("well with id: " + wellId + " was not found");
        }
    }

    //done
    @Override
    public List<WellResponse> getAllWells() {
        List<Well> wellEntities = wellRepository.findAll();
        List<WellResponse> wellResponseList = new ArrayList<>();
        Type listType = new TypeToken<List<WellResponse>>() {
        }.getType();
        wellResponseList = modelMapper.map(wellEntities, listType);
        for (int i = 0; i < wellEntities.size(); i++) {
            wellResponseList.get(i).setFieldId(wellEntities.get(i).getField().getFieldId());
        }
//        List<WellResponse> wellResponses = wellEntities.
//                stream().map(well-> modelMapper.map(well,WellResponse.class))
//                .collect(Collectors.toList());

        return wellResponseList;
    }

    //done
    @Override
    public WellResponse getWellByID(Integer wellId) {
        WellResponse wellResponse = modelMapper.map(wellRepository.findById(wellId).get(), WellResponse.class);
        wellResponse.setFieldId(wellRepository.findById(wellId).get().getField().getFieldId());
        return wellResponse;
    }

    //dond
    @Override
    public boolean delete(Integer wellId) {
        WellResponse wellResponse;
        wellResponse = (WellResponse) getWellByID(wellId);

        if (wellResponse != null) {
            wellRepository.deleteById(wellId);
            return true;
        } else {
            return false;
        }

    }
}
