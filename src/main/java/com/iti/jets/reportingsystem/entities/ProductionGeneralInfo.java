package com.iti.jets.reportingsystem.entities;
// Generated Jun 7, 2021, 4:22:33 AM by Hibernate Tools 6.0.0.Alpha2


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * ProductionGeneralInfo generated by hbm2java
 */
@Entity
@Table(name = "production_general_info"
        , catalog = "reportingsystem"
)
public class ProductionGeneralInfo implements java.io.Serializable {
    private Integer id;
    @JsonBackReference
    private Well well;
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

    public ProductionGeneralInfo() {
    }


    public ProductionGeneralInfo(Well well) {
        this.well = well;
    }

    public ProductionGeneralInfo(Well well, Date initialProdDate, String initialType, String initialProduct, String initialLiftType, String initStatus, String monitoringSystem, Date currentWellTypeDate, String currentWellType, String currentProduct, String currentStatus, Integer runtime, String currentLiftType, Date currentLiftTypeDate, String powerSourceType, String powerSource, String processionPlant) {
        this.well = well;
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

    @Id
    @GeneratedValue(strategy = IDENTITY)


    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "well_id", nullable = false)
    public Well getWell() {
        return this.well;
    }

    public void setWell(Well well) {
        this.well = well;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "initial_prod_date", length = 10)
    public Date getInitialProdDate() {
        return this.initialProdDate;
    }

    public void setInitialProdDate(Date initialProdDate) {
        this.initialProdDate = initialProdDate;
    }


    @Column(name = "initial_type", length = 19)
    public String getInitialType() {
        return this.initialType;
    }

    public void setInitialType(String initialType) {
        this.initialType = initialType;
    }


    @Column(name = "initial_product", length = 14)
    public String getInitialProduct() {
        return this.initialProduct;
    }

    public void setInitialProduct(String initialProduct) {
        this.initialProduct = initialProduct;
    }


    @Column(name = "initial_lift_type", length = 11)
    public String getInitialLiftType() {
        return this.initialLiftType;
    }

    public void setInitialLiftType(String initialLiftType) {
        this.initialLiftType = initialLiftType;
    }


    @Column(name = "init_status", length = 9)
    public String getInitStatus() {
        return this.initStatus;
    }

    public void setInitStatus(String initStatus) {
        this.initStatus = initStatus;
    }


    @Column(name = "monitoring_system", length = 5)
    public String getMonitoringSystem() {
        return this.monitoringSystem;
    }

    public void setMonitoringSystem(String monitoringSystem) {
        this.monitoringSystem = monitoringSystem;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "current_well_type_date", length = 10)
    public Date getCurrentWellTypeDate() {
        return this.currentWellTypeDate;
    }

    public void setCurrentWellTypeDate(Date currentWellTypeDate) {
        this.currentWellTypeDate = currentWellTypeDate;
    }


    @Column(name = "current_well_type", length = 19)
    public String getCurrentWellType() {
        return this.currentWellType;
    }

    public void setCurrentWellType(String currentWellType) {
        this.currentWellType = currentWellType;
    }


    @Column(name = "current_product", length = 14)
    public String getCurrentProduct() {
        return this.currentProduct;
    }

    public void setCurrentProduct(String currentProduct) {
        this.currentProduct = currentProduct;
    }


    @Column(name = "current_status", length = 9)
    public String getCurrentStatus() {
        return this.currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }


    @Column(name = "runtime")
    public Integer getRuntime() {
        return this.runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }


    @Column(name = "current_lift_type", length = 11)
    public String getCurrentLiftType() {
        return this.currentLiftType;
    }

    public void setCurrentLiftType(String currentLiftType) {
        this.currentLiftType = currentLiftType;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "current_lift_type_date", length = 10)
    public Date getCurrentLiftTypeDate() {
        return this.currentLiftTypeDate;
    }

    public void setCurrentLiftTypeDate(Date currentLiftTypeDate) {
        this.currentLiftTypeDate = currentLiftTypeDate;
    }


    @Column(name = "power_source_type", length = 16)
    public String getPowerSourceType() {
        return this.powerSourceType;
    }

    public void setPowerSourceType(String powerSourceType) {
        this.powerSourceType = powerSourceType;
    }


    @Column(name = "power_source", length = 45)
    public String getPowerSource() {
        return this.powerSource;
    }

    public void setPowerSource(String powerSource) {
        this.powerSource = powerSource;
    }


    @Column(name = "procession_plant", length = 9)
    public String getProcessionPlant() {
        return this.processionPlant;
    }

    public void setProcessionPlant(String processionPlant) {
        this.processionPlant = processionPlant;
    }


}


