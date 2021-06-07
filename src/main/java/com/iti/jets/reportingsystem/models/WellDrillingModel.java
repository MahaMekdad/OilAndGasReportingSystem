package com.iti.jets.reportingsystem.models;

import com.iti.jets.reportingsystem.entities.DrillingInfo;
import com.iti.jets.reportingsystem.entities.Field;

import java.util.HashSet;
import java.util.Set;

public class WellDrillingModel {
    private Integer wellId;
    private String wellName;
    private String wellCode;
//    private Set<DrillingInfo> drillingInfos = new HashSet<DrillingInfo>(0);

//    public Set<DrillingInfo> getDrillingInfos() {
//        return drillingInfos;
//    }
//
//    public void setDrillingInfos(Set<DrillingInfo> drillingInfos) {
//        this.drillingInfos = drillingInfos;
//    }

    WellDrillingModel(){}

    public WellDrillingModel(Integer wellId, String wellName, String wellCode) {
        this.wellId = wellId;
        this.wellName = wellName;
        this.wellCode = wellCode;
    }

    public Integer getWellId() {
        return wellId;
    }

    public void setWellId(Integer wellId) {
        this.wellId = wellId;
    }

    public String getWellName() {
        return wellName;
    }

    public void setWellName(String wellName) {
        this.wellName = wellName;
    }

    public String getWellCode() {
        return wellCode;
    }

    public void setWellCode(String wellCode) {
        this.wellCode = wellCode;
    }
}
