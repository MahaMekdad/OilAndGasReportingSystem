package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.services.FluidLevelMeasurementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wells")
public class FluidLevelMeasurementsController {

    @Autowired
    private FluidLevelMeasurementsService flmService;
}
