package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.models.DrillingInfoModel;
import com.iti.jets.reportingsystem.services.DrillingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wells")
public class DrillingInfoController {
    @Autowired
    private DrillingInfoService drillingInfoService;

    @GetMapping("/drillingInfo")
    public List<DrillingInfoModel> getAllDrillingInfo() {
        List<DrillingInfoModel> drillingInfoModelList = new ArrayList<>();
        drillingInfoModelList = drillingInfoService.getAllDrillingInfo();
        return drillingInfoModelList;
    }

    @GetMapping("/{id}/drillingInfo")
    public List<DrillingInfoModel> getForWellId(@PathVariable int id) {
        List<DrillingInfoModel> drillingInfoModel = drillingInfoService.getForWellId(id);

        return drillingInfoModel;
    }

    @DeleteMapping("{id}/drillingInfo")
    public void deleteDrillingInfoForWell(@PathVariable int id) {
        drillingInfoService.delete(id);

    }

    @PostMapping("/{id}/drillingInfo")
    public void create(@RequestBody DrillingInfoModel drillingInfoModel ,@PathVariable int id) {
        System.out.println("drillingInfoModel == " + drillingInfoModel);
        drillingInfoService.creat(drillingInfoModel , id);

    }

    @GetMapping("/{wellId}/drillingInfo/{id}")
    public DrillingInfoModel getWellForId(@PathVariable int wellId, @PathVariable int id) {
      return drillingInfoService.getWellForId(wellId , id);
    }
    @DeleteMapping("/{wellId}/drillingInfo/{id}")
    public void deleteForId(@PathVariable int wellId, @PathVariable int id)
    {
        drillingInfoService.deleteWellInSpecificId(wellId , id);
    }
    @PutMapping("/{wellId}/drillingInfo/{id}")
    public void updateWellForId(@PathVariable int wellId, @PathVariable int id , @RequestBody DrillingInfoModel drillingInfoModel)
    {
        drillingInfoService.updateWellForId(wellId ,id ,drillingInfoModel);
    }
}
