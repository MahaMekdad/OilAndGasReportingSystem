package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.repos.FluidLevelMeasurementsRepository;
import com.iti.jets.reportingsystem.services.FluidLevelMeasurementsService;
import org.springframework.beans.factory.annotation.Autowired;

public class FluidLevelMeasurementsImpl implements FluidLevelMeasurementsService {

    @Autowired
    private FluidLevelMeasurementsRepository flmRepo;
}
