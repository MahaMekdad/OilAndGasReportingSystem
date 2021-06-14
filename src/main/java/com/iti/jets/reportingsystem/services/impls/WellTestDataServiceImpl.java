package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.WellTestRequest;
import com.iti.jets.openapi.model.WellTestResponse;
import com.iti.jets.reportingsystem.entities.WellTestData;
import com.iti.jets.reportingsystem.repos.WellRepository;
import com.iti.jets.reportingsystem.repos.WellTestDataRepository;
import com.iti.jets.reportingsystem.services.WellTestDataService;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WellTestDataServiceImpl implements WellTestDataService {

    private WellTestDataRepository wellTestRepo;
    private WellRepository wellRepository;
    ModelMapper modelMapper;

    @Autowired
    public WellTestDataServiceImpl(WellTestDataRepository wellTestRepo, WellRepository wellRepository) {
        this.wellTestRepo = wellTestRepo;
        this.wellRepository = wellRepository;
        this.modelMapper = new ModelMapper();
        //It's a common practice to set the fieldMatchingEnabled property to true and allow private field matching
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Override
    public List<WellTestResponse> getAllTestsForWells() {

        //get a list of test Entities
        List<WellTestData> wellTestEntities = wellTestRepo.findAll();

        //convert entities into DTOs and return them
        List<WellTestResponse> wellTestResponses = wellTestEntities
                .stream().map(wellTestData -> modelMapper.map(wellTestData, WellTestResponse.class))
                .collect(Collectors.toList());
        //returning DTOs
        return wellTestResponses;
    }

    @Override
    public List<WellTestResponse> getAllTestsForAWell(int wellId) {
        //get a list of All well  test Entities for certain wells.
        List<WellTestData> wellTestEntities = wellTestRepo.findAllByWellId(wellId);

        //convert entities into DTOs and return them
        List<WellTestResponse> wellTestResponseList = wellTestEntities
                .stream().map(wellTestData -> modelMapper.map(wellTestData, WellTestResponse.class))
                .collect(Collectors.toList());

        //returning DTOs
        return wellTestResponseList;
    }

    @Override
    public WellTestResponse insert(int wellId,WellTestRequest wellTestRequest) {

        //map dto into entity
        WellTestData wellTestDataEntity = modelMapper.map(wellTestRequest, WellTestData.class);

        //setting the well object
        wellTestDataEntity.setProductionDate(Date.from(Instant.from(wellTestRequest.getProductionDate())));
        wellTestDataEntity.setWell(wellRepository.findById(wellId).get());

        //save entity to the database.
        wellTestRepo.save(wellTestDataEntity);

        //return the model to the ui
        return modelMapper.map(wellTestRequest, WellTestResponse.class);
    }

    @Override
    public WellTestResponse updateSpecificTest(int wellId, int recordId, WellTestRequest wellTestRequest) {
        //convert dto to Entity, save it to the database
        WellTestData entity = modelMapper.map(wellTestRequest, WellTestData.class);

        //to be tested
        entity.setWell(wellRepository.findById(wellId).get());

        wellTestRepo.save(entity);

        return modelMapper.map(wellTestRequest, WellTestResponse.class);
    }

    @Override
    public void deleteTestRecordByWellIdAndRecordId(int wellId, int testId) {
        Optional<WellTestData> optionalWellTestData = wellTestRepo.findByWellIdAndTestId(wellId, testId);
        if (optionalWellTestData.isPresent()) {
            wellTestRepo.deleteByWellIdAndRecordId(wellId, testId);
        } else {
            throw new EntityNotFoundException("Test with well id : " + wellId + "and Record id :" + testId + " was not found");
        }

    }

}
