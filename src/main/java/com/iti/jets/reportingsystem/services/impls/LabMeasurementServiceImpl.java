package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.ChockTypeEnum;
import com.iti.jets.openapi.model.LabMeasurementRequest;
import com.iti.jets.openapi.model.LabMeasurementResponse;
import com.iti.jets.reportingsystem.entities.DailyActions;
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
        LabMesurement labMesurement = labMeasurementRepository.findById(labId).get();
        modelMapper.map(labMeasurementRequest, labMesurement);
        labMeasurementRepository.saveAndFlush(labMesurement);
    }

    @Override
    public List<LabMeasurementResponse> getAllLabs() {
        List<LabMesurement> returnedList = labMeasurementRepository.findAll();
        Type listType = new TypeToken<List<LabMeasurementResponse>>(){}.getType();
        List<LabMeasurementResponse> list;
        list = modelMapper.map(returnedList,listType);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setWellId(Long.valueOf(returnedList.get(i).getWell().getWellId()));
            list.get(i).setDate(OffsetDateTimeHelper.dateHelper(returnedList.get(i).getDate()));
        }
        return list;
    }

    @Override
    public List<LabMeasurementResponse> getAllLabsFromWell(Integer wellId) {
        List<LabMesurement> returnedList = labMeasurementRepository.findAllByWell_WellIdEquals(wellId);
        Type listType = new TypeToken<List<LabMeasurementResponse>>(){}.getType();
        List<LabMeasurementResponse> list;
        list = modelMapper.map(returnedList , listType);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setWellId(Long.valueOf(returnedList.get(i).getWell().getWellId()));
            list.get(i).setDate(OffsetDateTimeHelper.dateHelper(returnedList.get(i).getDate()));
        }
        return list;
    }

    @Override
    public List<LabMeasurementResponse> getAllLabs(Date beginDate, Date endDate){
        List<LabMesurement> returnedList = labMeasurementRepository.findAllByDateGreaterThanEqualAndDateLessThanEqual(beginDate, endDate);
        Type listType = new TypeToken<List<LabMeasurementResponse>>(){}.getType();
        List<LabMeasurementResponse> list;
        list = modelMapper.map(returnedList,listType);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setWellId(Long.valueOf(returnedList.get(i).getWell().getWellId()));
            list.get(i).setDate(OffsetDateTimeHelper.dateHelper(returnedList.get(i).getDate()));
        }
        return list;
    }

    @Override
    public List<LabMeasurementResponse> getAllLabsFromWell(Integer wellId ,Date beginDate, Date endDate){
        List<LabMesurement> returnedList = labMeasurementRepository.findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(wellId,beginDate,endDate);
        Type listType = new TypeToken<List<LabMeasurementResponse>>(){}.getType();
        List<LabMeasurementResponse> list;
        list = modelMapper.map(returnedList,listType);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setWellId(Long.valueOf(returnedList.get(i).getWell().getWellId()));
            list.get(i).setDate(OffsetDateTimeHelper.dateHelper(returnedList.get(i).getDate()));
        }
        return list;
    }

    @Override
    public LabMeasurementResponse getAlabFromAwell(Integer wellId, Integer labId) {
        LabMeasurementResponse labMeasurementResponse = new LabMeasurementResponse();
        Type listType = new TypeToken<LabMeasurementResponse>(){}.getType();
        labMeasurementResponse = modelMapper.map(labMeasurementRepository.getById(labId) , listType);
        return labMeasurementResponse;
    }

    @Override
    public boolean delete(Integer wellId, Integer labId) {
        labMeasurementRepository.deleteById(labId);
        return true;
    }
}
