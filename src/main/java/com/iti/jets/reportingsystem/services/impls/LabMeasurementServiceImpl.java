package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.ChockTypeEnum;
import com.iti.jets.openapi.model.LabMeasurementRequest;
import com.iti.jets.openapi.model.LabMeasurementResponse;
import com.iti.jets.reportingsystem.entities.LabMesurement;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.repos.LabMeasurementRepository;
import com.iti.jets.reportingsystem.repos.WellRepo;
import com.iti.jets.reportingsystem.services.LabMeasurementService;
import com.iti.jets.reportingsystem.utils.mappers.LabMeasurementMapper;
import com.iti.jets.reportingsystem.utils.mappers.helpers.OffsetDateTimeHelper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabMeasurementServiceImpl implements LabMeasurementService {

    private LabMeasurementRepository labMeasurementRepository;

    private WellRepo wellRepo;

    private ModelMapper modelMapper;

    private LabMeasurementMapper labMeasurementMapper;

    public LabMeasurementServiceImpl(LabMeasurementRepository labMeasurementRepository, WellRepo wellRepo, ModelMapper modelMapper, LabMeasurementMapper labMeasurementMapper) {
        this.labMeasurementRepository = labMeasurementRepository;
        this.wellRepo = wellRepo;
        this.modelMapper = modelMapper;
        this.labMeasurementMapper = labMeasurementMapper;
    }

    @Override
    public void insert(Integer wellId, LabMeasurementRequest labMeasurementRequest) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with the given id");
        }

        Well well = wellRepo.findById(wellId).isPresent() ?
                wellRepo.findById(wellId).get() : null;
        if(well == null) {
            System.out.println("no well with given id");
            return;
        }
        LabMesurement labMesurement = labMeasurementMapper.labMesurementMap(labMeasurementRequest);
        labMesurement.setWell(well);
        labMesurement.setDate(Date.from(labMeasurementRequest.getDate().toInstant()));
        labMeasurementRepository.saveAndFlush(labMesurement);

    }

    @Override
    public void update(Integer wellId, Integer labId, LabMeasurementRequest labMeasurementRequest) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with the given id");
        }
        List<LabMesurement> list = new ArrayList<>();
        LabMesurement labMesurement = new LabMesurement();
        LabMesurement labMesurement2 = new LabMesurement();
        list = labMeasurementRepository.findAllByWell_WellIdEquals(wellId);
        labMesurement2 = list.get(labId-1);

        if(labMesurement2 != null){
            labMesurement = labMeasurementMapper.labMesurementMap(labMeasurementRequest);
            labMesurement.setId(labMesurement2.getId());
            labMesurement.setWell(labMesurement2.getWell());
            labMeasurementRepository.saveAndFlush(labMesurement);
        } else {
            throw new ResourceNotFoundException("No resource found with the given Id") ;
        }
    }

    @Override
    public List<LabMeasurementResponse> getAllLabs() {
        List<LabMesurement> returnedList = labMeasurementRepository.findAll();
        Type listType = new TypeToken<List<LabMeasurementResponse>>(){}.getType();
        List<LabMeasurementResponse> list;
        list = modelMapper.map(returnedList,listType);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setDate(OffsetDateTimeHelper.dateHelper(returnedList.get(i).getDate()));
        }
        return list;
    }

    @Override
    public List<LabMeasurementResponse> getAllLabsFromWell(Integer wellId) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with the given id");
        }
        List<LabMesurement> returnedList = labMeasurementRepository.findAllByWell_WellIdEquals(wellId);
        Type listType = new TypeToken<List<LabMeasurementResponse>>(){}.getType();
        List<LabMeasurementResponse> list;
        list = modelMapper.map(returnedList , listType);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setDate(OffsetDateTimeHelper.dateHelper(returnedList.get(i).getDate()));
        }
        return list;
    }

    @Override
    public List<LabMeasurementResponse> getAllLabs(Date beginDate, Date endDate){
        Type listType = new TypeToken<List<LabMeasurementResponse>>(){}.getType();
        List<LabMeasurementResponse> resultList;
        resultList = modelMapper.map(labMeasurementRepository.findAllByDateGreaterThanEqualAndDateLessThanEqual(beginDate, endDate) , listType);
        return resultList;
    }

    @Override
    public List<LabMeasurementResponse> getAllLabsFromWell(Integer wellId ,Date beginDate, Date endDate){
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with the given id");
        }
        List<LabMesurement> returnedList = labMeasurementRepository.findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(wellId, beginDate, endDate);
        if(wellRepo.findById(wellId).isPresent() && !returnedList.isEmpty()){
            Type listType = new TypeToken<List<LabMeasurementResponse>>(){}.getType();
            List<LabMeasurementResponse> resultList;
            resultList = modelMapper.map(labMeasurementRepository.findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(wellId, beginDate, endDate) , listType);
            for (int i = 0; i < resultList.size(); i++) {
                resultList.get(i).setDate(OffsetDateTimeHelper.dateHelper(returnedList.get(i).getDate()));
            }
            return resultList;
        }  else {
            throw new ResourceNotFoundException("No well found with the given Id");
        }
    }

    @Override
    public LabMeasurementResponse getAlabFromAwell(Integer wellId, Integer labId) {
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with the given id");
        }
        if(!labMeasurementRepository.findById(labId).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with the given id");
        }
        LabMesurement lab = labMeasurementRepository.findByWell_WellIdEqualsAndIdEquals(wellId, labId);
        return modelMapper.map(lab , LabMeasurementResponse.class);
//        List<LabMeasurementResponse> LabMesurementsList = new ArrayList<>();
//        LabMesurementsList=getAllLabsFromWell(wellId);
//        LabMeasurementResponse labMeasurementResponse = new LabMeasurementResponse();
//        labMeasurementResponse = LabMesurementsList.get(labId-1);
//        return labMeasurementResponse;
    }

    @Override
    public boolean delete(Integer wellId, Integer labId) {
        if(!labMeasurementRepository.findById(labId).isPresent())
        {
            throw new ResourceNotFoundException("There is no record with the given id");

        }
        if(!wellRepo.findById(wellId).isPresent())
        {
            throw new ResourceNotFoundException("There is no well with the given id");
        }
        LabMeasurementResponse labMeasurementResponse ;
        labMeasurementResponse=getAlabFromAwell(wellId,labId);
        if(labMeasurementResponse == null)
        {
            throw new ResourceNotFoundException("There no record found");
        }

        if(labMeasurementResponse != null) {
            labMeasurementRepository.deleteByWellIdAndLabId(wellId , labId);
            return true;
        }
        else {
            return false;
        }

    }
}
