package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.AllFluidLevelMeasurementResponse;
import com.iti.jets.openapi.model.FluidLevelMeasurementRequest;
import com.iti.jets.openapi.model.FluidLevelMeasurementResponse;
import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.exceptions.ResourceNotFoundException;
import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import com.iti.jets.reportingsystem.repos.FluidLevelMeasurementsRepository;
import com.iti.jets.reportingsystem.repos.WellRepo;
import com.iti.jets.reportingsystem.services.FluidLevelMeasurementsService;
import com.iti.jets.reportingsystem.utils.mappers.FluidLevelMeasurementsMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Service
public class FluidLevelMeasurementsImpl implements FluidLevelMeasurementsService {

    private final FluidLevelMeasurementsRepository flmRepo;
    private final WellRepo wellRepo;
    private final ModelMapper modelMapper;
    private final FluidLevelMeasurementsMapper flmMapper;

    @Autowired
    public FluidLevelMeasurementsImpl(FluidLevelMeasurementsRepository flmRepo, WellRepo wellRepo, ModelMapper modelMapper, FluidLevelMeasurementsMapper flmMapper){
        this.flmRepo = flmRepo;
        this.wellRepo = wellRepo;
        this.modelMapper = modelMapper;
        this.flmMapper = flmMapper;
    }

    @Override
    public void delete(int flmId) {
        if(flmRepo.findById(flmId).isPresent()){
            flmRepo.deleteById(flmId);
        } else {
            throw new ResourceNotFoundException("No FLM found with the given id");
        }
    }

    @Override
    public void insert(FluidLevelMeasurementRequest fluidLevelMeasurementRequest, int wellId) {
        Well well = wellRepo.findById(wellId).isPresent() ?
                    wellRepo.findById(wellId).get() : null;
        if(well != null) {
            FluidLevelMeasurements flm = flmMapper.map(fluidLevelMeasurementRequest);
            flm.setWell(well);
            flmRepo.saveAndFlush(flm);
        } else {
            throw new ResourceNotFoundException("No FLM/Well found within the given well id");
        }
    }

    public List<AllFluidLevelMeasurementResponse> getAllFLMS() {
        Type listType = new TypeToken<List<AllFluidLevelMeasurementResponse>>(){}.getType();
        List<AllFluidLevelMeasurementResponse> resultList;
        resultList = modelMapper.map(flmRepo.findAll() , listType);
        return resultList;
    }

    @Override
    public List<AllFluidLevelMeasurementResponse> getAllFLMS(Date beginDate, Date endDate) {
        Type listType = new TypeToken<List<AllFluidLevelMeasurementResponse>>(){}.getType();
        List<AllFluidLevelMeasurementResponse> resultList;
        resultList = modelMapper.map(flmRepo.findAllByDateGreaterThanEqualAndDateLessThanEqual(beginDate, endDate) , listType);
        return resultList;

    }

    @Override
    public List<FluidLevelMeasurementResponse> getAllFLMSForAWell(int wellId) {
        if(wellRepo.findById(wellId).isPresent()){
            Type listType = new TypeToken<List<FluidLevelMeasurementResponse>>(){}.getType();
            List<FluidLevelMeasurementResponse> resultList;
            resultList = modelMapper.map(flmRepo.findAllByWell_WellIdEquals(wellId) , listType);
            return resultList;
        } else {
            throw new ResourceNotFoundException("No FLM/Well found within the given well id");
        }
    }

    @Override
    public List<FluidLevelMeasurementResponse> getAllFLMSForAWell(int wellId, Date beginDate, Date endDate) {
        List<FluidLevelMeasurements> returnedList = flmRepo.findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(wellId, beginDate, endDate);
        if(wellRepo.findById(wellId).isPresent() && !returnedList.isEmpty()){
            Type listType = new TypeToken<List<FluidLevelMeasurementResponse>>(){}.getType();
            List<FluidLevelMeasurementResponse> resultList;
            resultList = modelMapper.map(flmRepo.findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(wellId, beginDate, endDate) , listType);
            return resultList;
        } else if (!wellRepo.findById(wellId).isPresent()){
            throw new ResourceNotFoundException("No FLM/Well found within the given well id");
        } else {
            throw new ResourceNotFoundException("No FLM/Well records were found within the given dates");
        }
    }

    @Override
    public void updateSpecificFLMS(int wellId, int flmId, FluidLevelMeasurementRequest fluidLevelMeasurementRequest) {
        FluidLevelMeasurements flmObjToUpdate = flmRepo.findByWell_WellIdEqualsAndIdEquals(wellId, flmId);
        if(flmObjToUpdate != null){
            flmObjToUpdate = flmMapper.mapForPatch(fluidLevelMeasurementRequest, flmObjToUpdate);
            flmRepo.saveAndFlush(flmObjToUpdate);
        } else {
            throw new ResourceNotFoundException("No FLM/Well found with the given id");
        }
    }

    @Override
    public void deleteSpecificFLMS(int wellId, int flmId) {
        if(flmRepo.findByWell_WellIdEqualsAndIdEquals(wellId, flmId) != null){
            flmRepo.removeByWell_WellIdEqualsAndIdEquals(wellId, flmId);
        } else {
            throw new ResourceNotFoundException("No FLM/Well found with the given id");
        }
//        flmRepo.removeByWell_WellIdEqualsAndIdEquals(wellId, flmId);
    }
}
