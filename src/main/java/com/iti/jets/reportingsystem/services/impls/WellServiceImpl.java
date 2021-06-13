package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.LabMeasurementResponse;
import com.iti.jets.openapi.model.WellRequest;
import com.iti.jets.openapi.model.WellResponse;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.repos.WellRepository;
import com.iti.jets.reportingsystem.services.WellService;
import com.iti.jets.reportingsystem.utils.mappers.WellMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WellServiceImpl implements WellService {

    private WellRepository wellRepository;
    private ModelMapper modelMapper;
    private WellMapper wellMapper;

    @Autowired
    public WellServiceImpl(WellRepository wellRepository, ModelMapper modelMapper) {
        this.wellRepository = wellRepository;
        this.modelMapper = modelMapper;
//        this.wellMapper = wellMapper;
    }

    @Override
    public void insert(WellRequest wellRequest) {

        Well wellEntity = modelMapper.map(wellRequest,Well.class);
        wellRepository.saveAndFlush(wellEntity);

    }

    @Override
    public void update(int i, WellRequest wellRequest) {

    }

    @Override
    public List<WellResponse> getAllWells() {
        List<Well> wellEntities = wellRepository.findAll();
        List<WellResponse> wellResponses = wellEntities.
                stream().map(well-> modelMapper.map(well,WellResponse.class))
                .collect(Collectors.toList());

        return wellResponses;
    }

    @Override
    public WellResponse getWellByID(Integer wellId) {
        WellResponse wellResponse = modelMapper.map(wellRepository.findById(wellId).get(),WellResponse.class);

        return wellResponse;
    }


    @Override
    public boolean delete(Integer wellId) {
        WellResponse wellResponse ;
        wellResponse= (WellResponse) getWellByID(wellId);

        if(wellResponse != null) {
            wellRepository.deleteById(wellId);
            return true;
        }
        else {
            return false;
        }

    }
}
