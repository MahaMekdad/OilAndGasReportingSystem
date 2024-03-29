package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.ChockTypeEnum;
import com.iti.jets.openapi.model.FieldsBudgetAndActualResponse;
import com.iti.jets.openapi.model.WellTestRequest;
import com.iti.jets.openapi.model.WellTestResponse;
import com.iti.jets.reportingsystem.entities.BudgetActual;
import com.iti.jets.reportingsystem.entities.WellTestData;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.repos.WellRepository;
import com.iti.jets.reportingsystem.repos.WellTestDataRepository;
import com.iti.jets.reportingsystem.services.WellTestDataService;
import com.iti.jets.reportingsystem.utils.mappers.helpers.OffsetDateTimeHelper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
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
        if(!wellRepository.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with this id");
        }
        //get a list of All well  test Entities for certain wells.
        List<WellTestData> wellTestEntities = wellTestRepo.findAllByWellId(wellId);
        if(wellTestEntities==null)
        {
            throw new ResourceNotFoundException("There is no test data for this well");
        }

        List<WellTestResponse> responseList = new ArrayList<>();
        Type listType = new TypeToken<List<WellTestResponse>>() {}.getType();
        responseList = modelMapper.map(wellTestEntities, listType);
        for (int i = 0; i < responseList.size(); i++) {
            responseList.get(i).setChockType(ChockTypeEnum.valueOf(wellTestEntities.get(i).getChockType().toUpperCase()));
            responseList.get(i).setProductionDate(OffsetDateTimeHelper.dateHelper(wellTestEntities.get(i).getProductionDate()));

        //convert entities into DTOs and return them
//        List<WellTestResponse> wellTestResponseList = wellTestEntities
//                .stream().map(wellTestData -> modelMapper.map(wellTestData, WellTestResponse.class))
//                .collect(Collectors.toList());
            //returning DTOs
        }
        return responseList;
    }

    @Override
    public WellTestResponse insert(int wellId, WellTestRequest wellTestRequest) {
        if(!wellRepository.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with this id");
        }
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
        if(!wellTestRepo.findById(recordId).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with this id");
        }
        if(!wellRepository.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with this id");
        }
        //to be tested
        entity.setWell(wellRepository.findById(wellId).get());
        entity.setProductionDate(Date.from(wellTestRequest.getProductionDate().toInstant()));
        System.out.println(entity.getProductionDate() + " <-------------");
        entity.setId(recordId);
        wellTestRepo.save(entity);

        return modelMapper.map(wellTestRequest, WellTestResponse.class);
    }

    @Override
    public void deleteTestRecordByWellIdAndRecordId(int wellId, int testId) {
        if(!wellTestRepo.findById(testId).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with this id");
        }
        if(!wellRepository.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with this id");
        }
        Optional<WellTestData> optionalWellTestData = wellTestRepo.findByWellIdAndTestId(wellId, testId);
        if (optionalWellTestData.isPresent()) {
            wellTestRepo.deleteByWellIdAndRecordId(wellId, testId);
        } else {
            throw new EntityNotFoundException("Test with well id : " + wellId + "and Record id :" + testId + " was not found");
        }

    }

}
