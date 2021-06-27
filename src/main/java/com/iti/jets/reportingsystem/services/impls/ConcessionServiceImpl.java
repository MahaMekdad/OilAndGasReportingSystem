package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.ConcessionRequest;
import com.iti.jets.openapi.model.ConcessionResponse;
import com.iti.jets.reportingsystem.entities.Concession;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.repos.ConcessionRepository;
import com.iti.jets.reportingsystem.services.ConcessionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConcessionServiceImpl implements ConcessionService {

    private final ConcessionRepository concessionRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ConcessionServiceImpl(ConcessionRepository concessionRepository) {
        this.concessionRepository = concessionRepository;
        modelMapper = new ModelMapper();
        //It's a common practice to set the fieldMatchingEnabled property to true and allow private field matching
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Override
    public ConcessionResponse addConcession(ConcessionRequest concessionRequest) {
        //concert the concession model coming from the user to concession entity
        Concession entity = modelMapper.map(concessionRequest, Concession.class);
        //saving the entity to the database
        concessionRepository.save(entity);
        return modelMapper.map(entity, ConcessionResponse.class);
    }

    @Override
    public ConcessionResponse findConcessionById(int id) {
        if(!concessionRepository.findById(id).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with id");
        }
        Concession concessionEntity = concessionRepository.findById(id).get();
        if (concessionEntity == null) {
            throw new EntityNotFoundException("Concession with Id " + id + " Was not found");
        }
        ConcessionResponse concessionResponse = modelMapper.map(concessionEntity, ConcessionResponse.class);
        return concessionResponse;
    }

    @Override
    public List<ConcessionResponse> findAllConcessions() {
        //returning list of concession entities
        List<Concession> concessionsEntities = concessionRepository.findAll();
        //mapping entities to DTOs
        List<ConcessionResponse> concessionResponses = concessionsEntities
                .stream().map(concession -> modelMapper.map(concession, ConcessionResponse.class))
                .collect(Collectors.toList());
        //returning DTOs
        return concessionResponses;
    }

    @Override
    public ConcessionResponse updateConcession(int concessionId, ConcessionRequest concessionRequest) {
        if(!concessionRepository.findById(concessionId).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with id");
        }
        Optional<Concession> concessionById = concessionRepository.findById(concessionId);
        if (concessionById.isPresent()){
            Concession concession = concessionById.get();
            concession.setConcessionName(concessionRequest.getName());
            Concession savedEntity = concessionRepository.save(concession);

            //mapping the saved to the concession response and returning it.
            ConcessionResponse concessionResponse = modelMapper.map(savedEntity,ConcessionResponse.class);
            return concessionResponse;
        }else{
            throw new EntityNotFoundException("concession with id: " + concessionId + " was not found");
        }
    }

    @Override
    public void deleteConcession(int id) {
        if(!concessionRepository.findById(id).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with id");
        }
        Optional<Concession> concessionEntity = concessionRepository.findById(id);
        concessionEntity.ifPresentOrElse(concessionRepository::delete, () -> {
            throw new EntityNotFoundException("concession With id: " + id + " Was not found");
        });
    }
}
