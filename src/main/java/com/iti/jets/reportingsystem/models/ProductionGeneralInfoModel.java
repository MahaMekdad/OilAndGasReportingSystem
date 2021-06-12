package com.iti.jets.reportingsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iti.jets.reportingsystem.entities.Well;

import java.util.Date;

public class ProductionGeneralInfoModel {

    private Integer id;
    @JsonIgnore
    private Well well;
    private Integer wellId;
    private Date initialProdDate;
    private String initialType;
    private String initialProduct;
    private String initialLiftType;
    private String initStatus;
    private String monitoringSystem;
    private Date currentWellTypeDate;
    private String currentWellType;
    private String currentProduct;
    private String currentStatus;
    private Integer runtime;
    private String currentLiftType;
    private Date currentLiftTypeDate;
    private String powerSourceType;
    private String powerSource;
    private String processionPlant;

    public ProductionGeneralInfoModel() {
    }

    public ProductionGeneralInfoModel(Well well, Integer wellId, Date initialProdDate, String initialType, String initialProduct, String initialLiftType, String initStatus, String monitoringSystem, Date currentWellTypeDate, String currentWellType, String currentProduct, String currentStatus, Integer runtime, String currentLiftType, Date currentLiftTypeDate, String powerSourceType, String powerSource, String processionPlant) {
        this.well = well;
        this.wellId = wellId;
        this.initialProdDate = initialProdDate;
        this.initialType = initialType;
        this.initialProduct = initialProduct;
        this.initialLiftType = initialLiftType;
        this.initStatus = initStatus;
        this.monitoringSystem = monitoringSystem;
        this.currentWellTypeDate = currentWellTypeDate;
        this.currentWellType = currentWellType;
        this.currentProduct = currentProduct;
        this.currentStatus = currentStatus;
        this.runtime = runtime;
        this.currentLiftType = currentLiftType;
        this.currentLiftTypeDate = currentLiftTypeDate;
        this.powerSourceType = powerSourceType;
        this.powerSource = powerSource;
        this.processionPlant = processionPlant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Well getWell() {
        return well;
    }

    public void setWell(Well well) {
        this.well = well;
    }

    public Integer getWellId() {
        return wellId;
    }

    public void setWellId(Integer wellId) {
        this.wellId = wellId;
    }

    public Date getInitialProdDate() {
        return initialProdDate;
    }

    public void setInitialProdDate(Date initialProdDate) {
        this.initialProdDate = initialProdDate;
    }

    public String getInitialType() {
        return initialType;
    }

    public void setInitialType(String initialType) {
        this.initialType = initialType;
    }

    public String getInitialProduct() {
        return initialProduct;
    }

    public void setInitialProduct(String initialProduct) {
        this.initialProduct = initialProduct;
    }

    public String getInitialLiftType() {
        return initialLiftType;
    }

    public void setInitialLiftType(String initialLiftType) {
        this.initialLiftType = initialLiftType;
    }

    public String getInitStatus() {
        return initStatus;
    }

    public void setInitStatus(String initStatus) {
        this.initStatus = initStatus;
    }

    public String getMonitoringSystem() {
        return monitoringSystem;
    }

    public void setMonitoringSystem(String monitoringSystem) {
        this.monitoringSystem = monitoringSystem;
    }

    public Date getCurrentWellTypeDate() {
        return currentWellTypeDate;
    }

    public void setCurrentWellTypeDate(Date currentWellTypeDate) {
        this.currentWellTypeDate = currentWellTypeDate;
    }

    public String getCurrentWellType() {
        return currentWellType;
    }

    public void setCurrentWellType(String currentWellType) {
        this.currentWellType = currentWellType;
    }

    public String getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(String currentProduct) {
        this.currentProduct = currentProduct;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getCurrentLiftType() {
        return currentLiftType;
    }

    public void setCurrentLiftType(String currentLiftType) {
        this.currentLiftType = currentLiftType;
    }

    public Date getCurrentLiftTypeDate() {
        return currentLiftTypeDate;
    }

    public void setCurrentLiftTypeDate(Date currentLiftTypeDate) {
        this.currentLiftTypeDate = currentLiftTypeDate;
    }

    public String getPowerSourceType() {
        return powerSourceType;
    }

    public void setPowerSourceType(String powerSourceType) {
        this.powerSourceType = powerSourceType;
    }

    public String getPowerSource() {
        return powerSource;
    }

    public void setPowerSource(String powerSource) {
        this.powerSource = powerSource;
    }

    public String getProcessionPlant() {
        return processionPlant;
    }

    public void setProcessionPlant(String processionPlant) {
        this.processionPlant = processionPlant;
    }
}
