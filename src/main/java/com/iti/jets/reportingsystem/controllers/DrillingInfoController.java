package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.models.DrillingInfoDto;
import com.iti.jets.reportingsystem.services.DrillingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wells")
public class DrillingInfoController {
    @Autowired
    private DrillingInfoService drillingInfoService;

    @GetMapping("/drillingInfo")
    public List<DrillingInfoDto> getAllDrillingInfo()
    {
        List<DrillingInfoDto> drillingInfoDtoList = new ArrayList<>();
        drillingInfoDtoList = drillingInfoService.getAllDrillingInfo();
        return drillingInfoDtoList;
    }

}
