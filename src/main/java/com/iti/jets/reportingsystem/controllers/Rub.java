package com.iti.jets.reportingsystem.controllers;

public class Rub {

    //    @Override
//    public List<FluidLevelMeasurementsModel> getAllFLMS() {
//        Type listType = new TypeToken<List<FluidLevelMeasurementsModel>>(){}.getType();
//        List<FluidLevelMeasurementsModel> resultList;
//        resultList = modelMapper.map(flmRepo.findAll() , listType);
////        return flmRepo.findAllByWell_WellIdEquals(wellId);
//        return resultList;
//
////        List<FluidLevelMeasurements> flmsList = flmRepo.getAllForWellId(wellId);
////        System.out.println(flmsList.get(0).getWell().getWellCode() + "<======");
////        System.out.println(flmsList.size() + "<======");
//
////        Well well = wellRepo.findById(wellId).isPresent() ? wellRepo.findById(wellId).get() : null;
////
////        if(well == null) {
////            System.out.println("SHIT");
////            return null;
////        }
////
////        Set<FluidLevelMeasurements> flmsSet = well.getFluidLevelMeasurementses();
////        List<FluidLevelMeasurements> flmsList = new ArrayList<>(flmsSet);
////
////        Type listType = new TypeToken<List<FluidLevelMeasurementsModel>>(){}.getType();
////        List<FluidLevelMeasurementsModel> resultList;
////        resultList = modelMapper.map(flmsList , listType );
//
////        List<FluidLevelMeasurementsModel> resultList =
////        flmRecords
////                .stream()
////                .map(flm -> modelMapper.map(flm, FluidLevelMeasurementsModel.class))
////                .collect(Collectors.toList());
//
////        return resultList;
//    }

    //    @Override
//    public List<FluidLevelMeasurementsModel> getAllFLMSForAWell(int wellId) {
//        Type listType = new TypeToken<List<FluidLevelMeasurementsModel>>(){}.getType();
//        List<FluidLevelMeasurementsModel> resultList;
//        resultList = modelMapper.map(flmRepo.findAllByWell_WellIdEquals(wellId) , listType);
////        return flmRepo.findAllByWell_WellIdEquals(wellId);
//        return resultList;
//
////        List<FluidLevelMeasurements> flmsList = flmRepo.getAllForWellId(wellId);
////        System.out.println(flmsList.get(0).getWell().getWellCode() + "<======");
////        System.out.println(flmsList.size() + "<======");
//
////        Well well = wellRepo.findById(wellId).isPresent() ? wellRepo.findById(wellId).get() : null;
////
////        if(well == null) {
////            System.out.println("SHIT");
////            return null;
////        }
////
////        Set<FluidLevelMeasurements> flmsSet = well.getFluidLevelMeasurementses();
////        List<FluidLevelMeasurements> flmsList = new ArrayList<>(flmsSet);
////
////        Type listType = new TypeToken<List<FluidLevelMeasurementsModel>>(){}.getType();
////        List<FluidLevelMeasurementsModel> resultList;
////        resultList = modelMapper.map(flmsList , listType );
//
////        List<FluidLevelMeasurementsModel> resultList =
////        flmRecords
////                .stream()
////                .map(flm -> modelMapper.map(flm, FluidLevelMeasurementsModel.class))
////                .collect(Collectors.toList());
//
////        return resultList;
//    }


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
}
