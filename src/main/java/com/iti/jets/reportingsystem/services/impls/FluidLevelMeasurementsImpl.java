package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.entities.Well;
import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import com.iti.jets.reportingsystem.repos.FluidLevelMeasurementsRepository;
import com.iti.jets.reportingsystem.repos.WellRepo;
import com.iti.jets.reportingsystem.services.FluidLevelMeasurementsService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class FluidLevelMeasurementsImpl implements FluidLevelMeasurementsService {

    @Autowired
    private FluidLevelMeasurementsRepository flmRepo;

    @Autowired
    private WellRepo wellRepo;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public void delete(int flmId) {
        flmRepo.deleteById(flmId);
    }

    @Override
    public void insert(FluidLevelMeasurementsModel fluidLevelMeasurementModel) {
        System.out.println(fluidLevelMeasurementModel.getWellId() + " <----------");
        Well well = wellRepo.findById(fluidLevelMeasurementModel.getWellId()).isPresent() ?
                    wellRepo.findById(fluidLevelMeasurementModel.getWellId()).get() : null;
        if(well == null) {
            System.out.println("no well with given id");
            return;
        }
        FluidLevelMeasurements flm = new FluidLevelMeasurements();
        flm.setWell(well); flm.setRemarks(fluidLevelMeasurementModel.getRemarks());
        flm.setCard(fluidLevelMeasurementModel.getCard()); flm.setFluidLevel(fluidLevelMeasurementModel.getFluidLevel());
        flm.setPumpSubmerge(fluidLevelMeasurementModel.getPumpSubmerge()); flm.setPumpDepth(fluidLevelMeasurementModel.getPumpDepth());
        flm.setLiqPercentage(fluidLevelMeasurementModel.getLiqPercentage()); flm.setFlType(fluidLevelMeasurementModel.getFlType());
        flm.setDate(fluidLevelMeasurementModel.getDate()); flm.setIntervals(fluidLevelMeasurementModel.getIntervals());
        flm.setPumpFillage(fluidLevelMeasurementModel.getPumpFillage());
        flmRepo.saveAndFlush(flm);
    }

    @Override
    public List<FluidLevelMeasurementsModel> getAllFLMS() {
        Type listType = new TypeToken<List<FluidLevelMeasurementsModel>>(){}.getType();
        List<FluidLevelMeasurementsModel> resultList;
        resultList = modelMapper.map(flmRepo.findAll() , listType);
//        return flmRepo.findAllByWell_WellIdEquals(wellId);
        return resultList;

//        List<FluidLevelMeasurements> flmsList = flmRepo.getAllForWellId(wellId);
//        System.out.println(flmsList.get(0).getWell().getWellCode() + "<======");
//        System.out.println(flmsList.size() + "<======");

//        Well well = wellRepo.findById(wellId).isPresent() ? wellRepo.findById(wellId).get() : null;
//
//        if(well == null) {
//            System.out.println("SHIT");
//            return null;
//        }
//
//        Set<FluidLevelMeasurements> flmsSet = well.getFluidLevelMeasurementses();
//        List<FluidLevelMeasurements> flmsList = new ArrayList<>(flmsSet);
//
//        Type listType = new TypeToken<List<FluidLevelMeasurementsModel>>(){}.getType();
//        List<FluidLevelMeasurementsModel> resultList;
//        resultList = modelMapper.map(flmsList , listType );

//        List<FluidLevelMeasurementsModel> resultList =
//        flmRecords
//                .stream()
//                .map(flm -> modelMapper.map(flm, FluidLevelMeasurementsModel.class))
//                .collect(Collectors.toList());

//        return resultList;
    }

    @Override
    public List<FluidLevelMeasurementsModel> getAllFLMSForAWell(int wellId) {
        Type listType = new TypeToken<List<FluidLevelMeasurementsModel>>(){}.getType();
        List<FluidLevelMeasurementsModel> resultList;
        resultList = modelMapper.map(flmRepo.findAllByWell_WellIdEquals(wellId) , listType);
//        return flmRepo.findAllByWell_WellIdEquals(wellId);
        return resultList;

//        List<FluidLevelMeasurements> flmsList = flmRepo.getAllForWellId(wellId);
//        System.out.println(flmsList.get(0).getWell().getWellCode() + "<======");
//        System.out.println(flmsList.size() + "<======");

//        Well well = wellRepo.findById(wellId).isPresent() ? wellRepo.findById(wellId).get() : null;
//
//        if(well == null) {
//            System.out.println("SHIT");
//            return null;
//        }
//
//        Set<FluidLevelMeasurements> flmsSet = well.getFluidLevelMeasurementses();
//        List<FluidLevelMeasurements> flmsList = new ArrayList<>(flmsSet);
//
//        Type listType = new TypeToken<List<FluidLevelMeasurementsModel>>(){}.getType();
//        List<FluidLevelMeasurementsModel> resultList;
//        resultList = modelMapper.map(flmsList , listType );

//        List<FluidLevelMeasurementsModel> resultList =
//        flmRecords
//                .stream()
//                .map(flm -> modelMapper.map(flm, FluidLevelMeasurementsModel.class))
//                .collect(Collectors.toList());

//        return resultList;
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
    public void updateSpecificFLMS(int wellId, int flmId, FluidLevelMeasurementsModel fluidLevelMeasurementModel) {
        FluidLevelMeasurements flmObjToUpdate = flmRepo.findByWell_WellIdEqualsAndIdEquals(wellId, flmId);
        System.out.println(flmObjToUpdate.getId() + ", " + flmObjToUpdate.getRemarks());

        if(fluidLevelMeasurementModel.getRemarks() != null){
            flmObjToUpdate.setRemarks(fluidLevelMeasurementModel.getRemarks());
        }
        if(fluidLevelMeasurementModel.getCard() != null){
            flmObjToUpdate.setCard(fluidLevelMeasurementModel.getCard());
        }
        if(fluidLevelMeasurementModel.getFluidLevel() != null){
            flmObjToUpdate.setFluidLevel(fluidLevelMeasurementModel.getFluidLevel());
        }
        if(fluidLevelMeasurementModel.getPumpSubmerge() != null){
            flmObjToUpdate.setPumpSubmerge(fluidLevelMeasurementModel.getPumpSubmerge());
        }
        if(fluidLevelMeasurementModel.getPumpDepth() != null){
            flmObjToUpdate.setPumpDepth(fluidLevelMeasurementModel.getPumpDepth());
        }
        if(fluidLevelMeasurementModel.getLiqPercentage() != null){
            flmObjToUpdate.setLiqPercentage(fluidLevelMeasurementModel.getLiqPercentage());
        }
        if(fluidLevelMeasurementModel.getFlType() != null){
            flmObjToUpdate.setFlType(fluidLevelMeasurementModel.getFlType());
        }
        if(fluidLevelMeasurementModel.getDate() != null){
            flmObjToUpdate.setDate(fluidLevelMeasurementModel.getDate());
        }
        if(fluidLevelMeasurementModel.getIntervals() != null){
            flmObjToUpdate.setIntervals(fluidLevelMeasurementModel.getIntervals());
        }
        if(fluidLevelMeasurementModel.getPumpFillage() != null){
            flmObjToUpdate.setPumpFillage(fluidLevelMeasurementModel.getPumpFillage());
        }
        flmRepo.saveAndFlush(flmObjToUpdate);
    }

    @Override
    public void deleteSpecificFLMS(int wellId, int flmId) {
        flmRepo.removeByWell_WellIdEqualsAndIdEquals(wellId, flmId);
    }
}
