package com.iti.jets.reportingsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iti.jets.reportingsystem.entities.Well;

import java.util.Date;

public class WellTestDataModel {
    private int id;
    private int wellId;
    @JsonIgnore
    private Well well;
    private Date productionDate;
    private Integer TDuration;
    private Double gross;
    private Double net;
    private Double wc;
    private Double gor;
    private Double gasRate;
    private Double condensate;
    private Double whp;
    private Double wht;
    private Double usp;
    private Double ust;
    private Double sp;
    private Double st;
    private Double flp;
    private Double flt;
    private String chockType;
    private Integer chockSize;
    private Double h2s;
    private Double co2;
    private String unit;
    private String remarks;

    //no arg constructor

    public WellTestDataModel() {
    }

    public WellTestDataModel(Date productionDate, Integer TDuration,
                             Double gross, Double net, Double wc, Double gor, Double gasRate,
                             Double condensate, Double whp, Double wht, Double usp, Double ust,
                             Double sp, Double st, Double flp, Double flt, String chockType,
                             Integer chockSize, Double h2s, Double co2, String unit,
                             String remarks) {
        this.productionDate = productionDate;
        this.TDuration = TDuration;
        this.gross = gross;
        this.net = net;
        this.wc = wc;
        this.gor = gor;
        this.gasRate = gasRate;
        this.condensate = condensate;
        this.whp = whp;
        this.wht = wht;
        this.usp = usp;
        this.ust = ust;
        this.sp = sp;
        this.st = st;
        this.flp = flp;
        this.flt = flt;
        this.chockType = chockType;
        this.chockSize = chockSize;
        this.h2s = h2s;
        this.co2 = co2;
        this.unit = unit;
        this.remarks = remarks;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWellId() {
        return wellId;
    }

    public void setWellId(int wellId) {

        if (well != null)
            this.wellId = well.getWellId();
        else
            this.wellId = wellId;
    }

    public Well getWell() {
        return well;
    }


    public void setWell(Well well) {
        this.well = well;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Integer getTDuration() {
        return TDuration;
    }

    public void setTDuration(Integer TDuration) {
        this.TDuration = TDuration;
    }

    public Double getGross() {
        return gross;
    }

    public void setGross(Double gross) {
        this.gross = gross;
    }

    public Double getNet() {
        return net;
    }

    public void setNet(Double net) {
        this.net = net;
    }

    public Double getWc() {
        return wc;
    }

    public void setWc(Double wc) {
        this.wc = wc;
    }

    public Double getGor() {
        return gor;
    }

    public void setGor(Double gor) {
        this.gor = gor;
    }

    public Double getGasRate() {
        return gasRate;
    }

    public void setGasRate(Double gasRate) {
        this.gasRate = gasRate;
    }

    public Double getCondensate() {
        return condensate;
    }

    public void setCondensate(Double condensate) {
        this.condensate = condensate;
    }

    public Double getWhp() {
        return whp;
    }

    public void setWhp(Double whp) {
        this.whp = whp;
    }

    public Double getWht() {
        return wht;
    }

    public void setWht(Double wht) {
        this.wht = wht;
    }

    public Double getUsp() {
        return usp;
    }

    public void setUsp(Double usp) {
        this.usp = usp;
    }

    public Double getUst() {
        return ust;
    }

    public void setUst(Double ust) {
        this.ust = ust;
    }

    public Double getSp() {
        return sp;
    }

    public void setSp(Double sp) {
        this.sp = sp;
    }

    public Double getSt() {
        return st;
    }

    public void setSt(Double st) {
        this.st = st;
    }

    public Double getFlp() {
        return flp;
    }

    public void setFlp(Double flp) {
        this.flp = flp;
    }

    public Double getFlt() {
        return flt;
    }

    public void setFlt(Double flt) {
        this.flt = flt;
    }

    public String getChockType() {
        return chockType;
    }

    public void setChockType(String chockType) {
        this.chockType = chockType;
    }

    public Integer getChockSize() {
        return chockSize;
    }

    public void setChockSize(Integer chockSize) {
        this.chockSize = chockSize;
    }

    public Double getH2s() {
        return h2s;
    }

    public void setH2s(Double h2s) {
        this.h2s = h2s;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "WellTestDataModel{" +
                "id=" + id +
                ", well=" + well +
                ", productionDate=" + productionDate +
                ", TDuration=" + TDuration +
                ", gross=" + gross +
                ", net=" + net +
                ", wc=" + wc +
                ", gor=" + gor +
                ", gasRate=" + gasRate +
                ", condensate=" + condensate +
                ", whp=" + whp +
                ", wht=" + wht +
                ", usp=" + usp +
                ", ust=" + ust +
                ", sp=" + sp +
                ", st=" + st +
                ", flp=" + flp +
                ", flt=" + flt +
                ", chockType='" + chockType + '\'' +
                ", chockSize=" + chockSize +
                ", h2s=" + h2s +
                ", co2=" + co2 +
                ", unit='" + unit + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
