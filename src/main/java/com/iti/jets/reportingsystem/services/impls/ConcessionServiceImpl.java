package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.Concession;
import com.iti.jets.reportingsystem.exceptions.EntityNotFoundException;
import com.iti.jets.reportingsystem.models.ConcessionModel;
import com.iti.jets.reportingsystem.repos.ConcessionRepository;
import com.iti.jets.reportingsystem.services.ConcessionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public ConcessionModel addConcession(ConcessionModel concessionModel) {
        //concert the concession model coming from the user to concession entity
        Concession entity = modelMapper.map(concessionModel, Concession.class);
        //saving the entity to the database
        concessionRepository.save(entity);
        return concessionModel;
    }

    @Override
    public ConcessionModel findConcessionById(Long id) {
        Concession concessionEntity = concessionRepository.findConcessionById(id).orElseThrow(() -> new EntityNotFoundException(
                "ConcessionModel by id" + id + "Was not found"
        ));

        //concert the entity to the model tobe returned
        ConcessionModel concessionModel = modelMapper.map(concessionEntity,ConcessionModel.class);
        return concessionModel;
    }

    @Override
    public List<ConcessionModel> findAllConcessions() {
        //returning list of concession entities
        List<Concession> concessionsEntities = concessionRepository.findAll();
        //mapping entities to DTOs
        List<ConcessionModel> concessionModels = concessionsEntities
                .stream().map(concession -> modelMapper.map(concession,ConcessionModel.class))
                .collect(Collectors.toList());
        //returning DTOs
        return concessionModels;
    }

    @Override
    public ConcessionModel updateConcession(ConcessionModel concessionModel) {
    //convert dto to entity and store it in the database
        Concession concessionEntity = modelMapper.map(concessionModel,Concession.class);
        concessionRepository.save(concessionEntity);
        return concessionModel;
    }

    @Override
    public void deleteConcession(Long id) {
        concessionRepository.deleteById(id);
    }
}
