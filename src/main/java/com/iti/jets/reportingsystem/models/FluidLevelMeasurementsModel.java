package com.iti.jets.reportingsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iti.jets.reportingsystem.entities.Well;

import java.util.Date;

public class FluidLevelMeasurementsModel {

    private int id;
    @JsonIgnore
    private Well well;
    private Integer wellId;
    private Date date;
    private String intervals;
    private String flType;
    private Double fluidLevel;
    private Double pumpDepth;
    private Long liqPercentage;
    private Long pumpFillage;
    private Double pumpSubmerge;
    private Character card;
    private String remarks;

    public FluidLevelMeasurementsModel() {}

    public FluidLevelMeasurementsModel(Well well, Date date, String intervals, String fltype, Double fluidLevel,
                                       Double pumpDepth, Long liqPercentage, Long pumpFillage, Double pumpSubmerge,
                                       Character card, String remarks) {
        this.well = well;
        this.date = date;
        this.intervals = intervals;
        this.flType = fltype;
        this.fluidLevel = fluidLevel;
        this.pumpDepth = pumpDepth;
        this.pumpSubmerge = pumpSubmerge;
        this.card = card;
        this.remarks = remarks;
    }

    public Integer getWellId() {
        return wellId;
    }

    public void setWellId(Integer wellId) {
        if(well != null)
            this.wellId = well.getWellId();
        else
            this.wellId = wellId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }

    public String getFlType() {
        return flType;
    }

    public void setFlType(String flType) {
        this.flType = flType;
    }

    public Double getFluidLevel() {
        return fluidLevel;
    }

    public void setFluidLevel(Double fluidLevel) {
        this.fluidLevel = fluidLevel;
    }

    public Double getPumpDepth() {
        return pumpDepth;
    }

    public void setPumpDepth(Double pumpDepth) {
        this.pumpDepth = pumpDepth;
    }

    public Long getLiqPercentage() {
        return liqPercentage;
    }

    public void setLiqPercentage(Long liqPercentage) {
        this.liqPercentage = liqPercentage;
    }

    public Long getPumpFillage() {
        return pumpFillage;
    }

    public void setPumpFillage(Long pumpFillage) {
        this.pumpFillage = pumpFillage;
    }

    public Double getPumpSubmerge() {
        return pumpSubmerge;
    }

    public void setPumpSubmerge(Double pumpSubmerge) {
        this.pumpSubmerge = pumpSubmerge;
    }

    public Character getCard() {
        return card;
    }

    public void setCard(Character card) {
        this.card = card;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
