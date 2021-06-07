package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.models.DrillingInfoModel;
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
    public List<DrillingInfoModel> getAllDrillingInfo()
    {
        List<DrillingInfoModel> drillingInfoModelList = new ArrayList<>();
        drillingInfoModelList = drillingInfoService.getAllDrillingInfo();
        return drillingInfoModelList;
    }
    @GetMapping("/drillingInfo/{id}")
    public DrillingInfoModel getForWellId(@PathVariable int id)
    {
        DrillingInfoModel drillingInfoModel = drillingInfoService.getForWellId(id);

        return drillingInfoModel;
    }

}
