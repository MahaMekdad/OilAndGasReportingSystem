package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.WellRequest;
import com.iti.jets.openapi.model.WellResponse;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.repos.WellRepository;
import com.iti.jets.reportingsystem.services.WellService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WellServiceImpl implements WellService {

    private WellRepository wellRepository;
    private ModelMapper modelMapper;

    @Autowired
    public WellServiceImpl(WellRepository wellRepository, ModelMapper modelMapper) {
        this.wellRepository = wellRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WellResponse insert(WellRequest wellRequest) {
        Well wellEntity = modelMapper.map(wellRequest, Well.class);
        System.out.println("wellEntity============////"+wellEntity.getField());

        wellRepository.saveAndFlush(wellEntity);
        return modelMapper.map(wellEntity, WellResponse.class);

    }


//done
    @Override
    public WellResponse updateWell(int wellId, WellRequest wellRequest) {
        Optional<Well> wellById = wellRepository.findById(wellId);
        if (wellById.isPresent()){
            Well well = wellById.get();
            well.setWellName(wellRequest.getWellName());
            Well savedEntity = wellRepository.save(well);
            WellResponse wellResponse = modelMapper.map(savedEntity,WellResponse.class);
            return wellResponse;
        }else{
            throw new EntityNotFoundException("well with id: " + wellId + " was not found");
        }
    }

//done
    @Override
    public List<WellResponse> getAllWells() {
        List<Well> wellEntities = wellRepository.findAll();
        List<WellResponse> wellResponses = wellEntities.
                stream().map(well-> modelMapper.map(well,WellResponse.class))
                .collect(Collectors.toList());

        return wellResponses;
    }
//done
    @Override
    public WellResponse getWellByID(Integer wellId) {
        WellResponse wellResponse = modelMapper.map(wellRepository.findById(wellId).get(),WellResponse.class);

        return wellResponse;
    }

//dond
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
