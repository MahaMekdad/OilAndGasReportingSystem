package com.iti.jets.reportingsystem.models;

import com.iti.jets.reportingsystem.entities.Well;

import java.util.Date;

public class FluidLevelMeasurementsModel {

    private int id;
    private Well well;
    private Date date;
    private String intervals;
    private String fltype;
    private Double fluidlLevel;
    private Double pumpDepth;
    private Long liqPercentage;
    private Long pumpFillage;
    private Double pumpSubmerge;
    private Character card;
    private String remarks;

    public FluidLevelMeasurementsModel() {}

    public FluidLevelMeasurementsModel(Well well, Date date, String intervals, String fltype, Double fluidlLevel,
                                       Double pumpDepth, Long liqPercentage, Long pumpFillage, Double pumpSubmerge,
                                       Character card, String remarks) {
        this.well = well;
        this.date = date;
        this.intervals = intervals;
        this.fltype = fltype;
        this.fluidlLevel = fluidlLevel;
        this.pumpDepth = pumpDepth;
        this.pumpSubmerge = pumpSubmerge;
        this.card = card;
        this.remarks = remarks;
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

    public String getFltype() {
        return fltype;
    }

    public void setFltype(String fltype) {
        this.fltype = fltype;
    }

    public Double getFluidlLevel() {
        return fluidlLevel;
    }

    public void setFluidlLevel(Double fluidlLevel) {
        this.fluidlLevel = fluidlLevel;
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

    @Override
    public String toString() {
        return "FluidLevelMeasurementsModel{" +
                "id=" + id +
                ", well=" + well +
                ", date=" + date +
                ", intervals='" + intervals + '\'' +
                ", fltype='" + fltype + '\'' +
                ", fluidlLevel=" + fluidlLevel +
                ", pumpDepth=" + pumpDepth +
                ", liqPercentage=" + liqPercentage +
                ", pumpFillage=" + pumpFillage +
                ", pumpSubmerge=" + pumpSubmerge +
                ", card=" + card +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
