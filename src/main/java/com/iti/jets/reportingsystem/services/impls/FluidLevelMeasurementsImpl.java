package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.openapi.model.AllFluidLevelMeasurementResponse;
import com.iti.jets.openapi.model.FluidLevelMeasurementRequest;
import com.iti.jets.openapi.model.FluidLevelMeasurementResponse;
import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.entities.Well;
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

    private FluidLevelMeasurementsRepository flmRepo;

    private WellRepo wellRepo;

    private ModelMapper modelMapper;

    private FluidLevelMeasurementsMapper flmMapper;

    @Autowired
    public FluidLevelMeasurementsImpl(FluidLevelMeasurementsRepository flmRepo, WellRepo wellRepo, ModelMapper modelMapper, FluidLevelMeasurementsMapper flmMapper){
        this.flmRepo = flmRepo;
        this.wellRepo = wellRepo;
        this.modelMapper = modelMapper;
        this.flmMapper = flmMapper;
    }

    @Override
    public void delete(int flmId) {
        flmRepo.deleteById(flmId);
    }

    @Override
    public void insert(FluidLevelMeasurementRequest fluidLevelMeasurementRequest, int wellId) {
        Well well = wellRepo.findById(wellId).isPresent() ?
                    wellRepo.findById(wellId).get() : null;
        if(well == null) {
            System.out.println("no well with given id");
            return;
        }
        FluidLevelMeasurements flm = flmMapper.map(fluidLevelMeasurementRequest);
        flm.setWell(well);
        flmRepo.saveAndFlush(flm);
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
        Type listType = new TypeToken<List<FluidLevelMeasurementResponse>>(){}.getType();
        List<FluidLevelMeasurementResponse> resultList;
        resultList = modelMapper.map(flmRepo.findAllByWell_WellIdEquals(wellId) , listType);
        return resultList;
    }

    @Override
    public List<FluidLevelMeasurementResponse> getAllFLMSForAWell(int wellId, Date beginDate, Date endDate) {
        Type listType = new TypeToken<List<FluidLevelMeasurementResponse>>(){}.getType();
        List<FluidLevelMeasurementResponse> resultList;
        resultList = modelMapper.map(flmRepo.findAllByWell_WellIdEqualsAndDateGreaterThanEqualAndDateLessThanEqual(wellId, beginDate, endDate) , listType);
        return resultList;
    }

//    @Override
//    public void updateAllFLMSForAWell(int wellId, FluidLevelMeasurements fluidLevelMeasurement) {
//        List<FluidLevelMeasurements> recordsReturned = flmRepo.findAllByWell_WellIdEquals(wellId);
//        for (FluidLevelMeasurements flm : recordsReturned) {
//            flm.setWell(fluidLevelMeasurement.getWell());
//            flm.setDate(fluidLevelMeasurement.getDate());
//            flm.setIntervals(fluidLevelMeasurement.getIntervals());
//            flm.setFltype(fluidLevelMeasurement.getFltype());
//            flm.setFluidlLevel(fluidLevelMeasurement.getFluidlLevel());
//            flm.setPumpDepth(fluidLevelMeasurement.getPumpDepth());
//            flm.setLiqPercentage(fluidLevelMeasurement.getLiqPercentage());
//            flm.setPumpFillage(fluidLevelMeasurement.getPumpFillage());
//            flm.setPumpSubmerge(fluidLevelMeasurement.getPumpSubmerge());
//            flm.setCard(fluidLevelMeasurement.getCard());
//            flm.setRemarks(fluidLevelMeasurement.getRemarks());
//            flmRepo.save(flm);
//        }
//        flmRepo.flush();
//    }
//
//    @Override
//    public void deleteAllFLMSForAWell(int wellId) {
//
//    }

    @Override
    public void updateSpecificFLMS(int wellId, int flmId, FluidLevelMeasurementRequest fluidLevelMeasurementRequest) {
        FluidLevelMeasurements flmObjToUpdate = flmRepo.findByWell_WellIdEqualsAndIdEquals(wellId, flmId);
        flmObjToUpdate = flmMapper.mapForPatch(fluidLevelMeasurementRequest, flmObjToUpdate);
        flmRepo.saveAndFlush(flmObjToUpdate);
    }

    @Override
    public void deleteSpecificFLMS(int wellId, int flmId) {
        flmRepo.removeByWell_WellIdEqualsAndIdEquals(wellId, flmId);
    }
}
