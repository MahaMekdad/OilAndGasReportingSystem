package com.iti.jets.reportingsystem.models;

import com.iti.jets.reportingsystem.entities.Well;

import java.util.Date;

public class DrillingInfoModel {
    private int id;
    private com.iti.jets.reportingsystem.entities.Well well;
    private Date releaseDate;
    private String wellDescription;
    private String wellType;
    private String boreType;
    private Integer measuredDepth;
    private Integer tvdDepth;
    private Integer bbtp;
    private String productionGeneralInfo;

    public DrillingInfoModel(Well well, Date releaseDate, String wellDescription, String wellType, String boreType, Integer measuredDepth, Integer tvdDepth, Integer bbtp, String productionGeneralInfo) {
        this.well = well;
        this.releaseDate = releaseDate;
        this.wellDescription = wellDescription;
        this.wellType = wellType;
        this.boreType = boreType;
        this.measuredDepth = measuredDepth;
        this.tvdDepth = tvdDepth;
        this.bbtp = bbtp;
        this.productionGeneralInfo = productionGeneralInfo;
    }

    public DrillingInfoModel(int id, Well well, Date releaseDate, String wellDescription, String wellType, String boreType, Integer measuredDepth, Integer tvdDepth, Integer bbtp, String productionGeneralInfo) {
        this.id = id;
        this.well = well;
        this.releaseDate = releaseDate;
        this.wellDescription = wellDescription;
        this.wellType = wellType;
        this.boreType = boreType;
        this.measuredDepth = measuredDepth;
        this.tvdDepth = tvdDepth;
        this.bbtp = bbtp;
        this.productionGeneralInfo = productionGeneralInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Well getWell() {
        return well;
    }

    public void setWell(Well well) {
        this.well = well;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getWellDescription() {
        return wellDescription;
    }

    public void setWellDescription(String wellDescription) {
        this.wellDescription = wellDescription;
    }

    public String getWellType() {
        return wellType;
    }

    public void setWellType(String wellType) {
        this.wellType = wellType;
    }

    public String getBoreType() {
        return boreType;
    }

    public void setBoreType(String boreType) {
        this.boreType = boreType;
    }

    public Integer getMeasuredDepth() {
        return measuredDepth;
    }

    public void setMeasuredDepth(Integer measuredDepth) {
        this.measuredDepth = measuredDepth;
    }

    public Integer getTvdDepth() {
        return tvdDepth;
    }

    public void setTvdDepth(Integer tvdDepth) {
        this.tvdDepth = tvdDepth;
    }

    public Integer getBbtp() {
        return bbtp;
    }

    public void setBbtp(Integer bbtp) {
        this.bbtp = bbtp;
    }

    public String getProductionGeneralInfo() {
        return productionGeneralInfo;
    }

    public void setProductionGeneralInfo(String productionGeneralInfo) {
        this.productionGeneralInfo = productionGeneralInfo;
    }
}
