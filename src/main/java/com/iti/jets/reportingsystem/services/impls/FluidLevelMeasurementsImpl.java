package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.entities.FluidLevelMeasurements;
import com.iti.jets.reportingsystem.models.FluidLevelMeasurementsModel;
import com.iti.jets.reportingsystem.repos.FluidLevelMeasurementsRepository;
import com.iti.jets.reportingsystem.services.FluidLevelMeasurementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FluidLevelMeasurementsImpl implements FluidLevelMeasurementsService {

    @Autowired
    private FluidLevelMeasurementsRepository flmRepo;

    @Override
    public void delete(int flmId) {
        flmRepo.deleteById(flmId);
    }

    @Override
    public void insert(FluidLevelMeasurements fluidLevelMeasurement) {
        flmRepo.saveAndFlush(fluidLevelMeasurement);
    }

    @Override
    public List<FluidLevelMeasurements> getAllFLMSForAWell(int wellId) {
        return flmRepo.findAllByWell_WellIdEquals(wellId);
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
    public void updateSpecificFLMS(int wellId, int flmId, FluidLevelMeasurements fluidLevelMeasurement) {

    }

    @Override
    public void deleteSpecificFLMS(int wellId, int flmId) {

    }
}
