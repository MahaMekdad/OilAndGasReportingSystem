package com.iti.jets.reportingsystem.entities;
// Generated Jun 7, 2021, 4:22:33 AM by Hibernate Tools 6.0.0.Alpha2


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Well generated by hbm2java
 */
@Entity
@Table(name="well"
    ,catalog="reportingsystem"
    , uniqueConstraints = {@UniqueConstraint(columnNames="well_code"), @UniqueConstraint(columnNames="well_name")} 
)

public class Well  implements java.io.Serializable {


     private Integer wellId;
     private Field field;
     private String wellName;
     private String wellCode;
     private Set<IntervalsInfo> intervalsInfos = new HashSet<IntervalsInfo>(0);
     private Set<WellTestData> wellTestDatas = new HashSet<WellTestData>(0);
     private Set<DailyActions> dailyActionses = new HashSet<DailyActions>(0);
     private Set<ProductionGeneralInfo> productionGeneralInfos = new HashSet<ProductionGeneralInfo>(0);
     private Set<DrillingInfo> drillingInfos = new HashSet<DrillingInfo>(0);
     private Set<FluidLevelMeasurements> fluidLevelMeasurementses = new HashSet<FluidLevelMeasurements>(0);
     private Set<LabMesurement> labMesurements = new HashSet<LabMesurement>(0);
     private Set<WellGeneralInfo> wellGeneralInfos = new HashSet<WellGeneralInfo>(0);

    public Well() {
    }

	
    public Well(Field field, String wellName, String wellCode) {
        this.field = field;
        this.wellName = wellName;
        this.wellCode = wellCode;
    }
    public Well(Field field, String wellName, String wellCode, Set<IntervalsInfo> intervalsInfos, Set<WellTestData> wellTestDatas, Set<DailyActions> dailyActionses, Set<ProductionGeneralInfo> productionGeneralInfos, Set<DrillingInfo> drillingInfos, Set<FluidLevelMeasurements> fluidLevelMeasurementses, Set<LabMesurement> labMesurements, Set<WellGeneralInfo> wellGeneralInfos) {
       this.field = field;
       this.wellName = wellName;
       this.wellCode = wellCode;
       this.intervalsInfos = intervalsInfos;
       this.wellTestDatas = wellTestDatas;
       this.dailyActionses = dailyActionses;
       this.productionGeneralInfos = productionGeneralInfos;
       this.drillingInfos = drillingInfos;
       this.fluidLevelMeasurementses = fluidLevelMeasurementses;
       this.labMesurements = labMesurements;
       this.wellGeneralInfos = wellGeneralInfos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="well_id", unique=true, nullable=false)
    public Integer getWellId() {
        return this.wellId;
    }
    
    public void setWellId(Integer wellId) {
        this.wellId = wellId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="field_id", nullable=false)
    public Field getField() {
        return this.field;
    }
    
    public void setField(Field field) {
        this.field = field;
    }

    
    @Column(name="well_name", unique=true, nullable=false, length=80)
    public String getWellName() {
        return this.wellName;
    }
    
    public void setWellName(String wellName) {
        this.wellName = wellName;
    }

    
    @Column(name="well_code", unique=true, nullable=false, length=80)
    public String getWellCode() {
        return this.wellCode;
    }
    
    public void setWellCode(String wellCode) {
        this.wellCode = wellCode;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="well")
    public Set<IntervalsInfo> getIntervalsInfos() {
        return this.intervalsInfos;
    }
    
    public void setIntervalsInfos(Set<IntervalsInfo> intervalsInfos) {
        this.intervalsInfos = intervalsInfos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="well")
    public Set<WellTestData> getWellTestDatas() {
        return this.wellTestDatas;
    }
    
    public void setWellTestDatas(Set<WellTestData> wellTestDatas) {
        this.wellTestDatas = wellTestDatas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="well")
    public Set<DailyActions> getDailyActionses() {
        return this.dailyActionses;
    }
    
    public void setDailyActionses(Set<DailyActions> dailyActionses) {
        this.dailyActionses = dailyActionses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="well")
    public Set<ProductionGeneralInfo> getProductionGeneralInfos() {
        return this.productionGeneralInfos;
    }
    
    public void setProductionGeneralInfos(Set<ProductionGeneralInfo> productionGeneralInfos) {
        this.productionGeneralInfos = productionGeneralInfos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="well")
    public Set<DrillingInfo> getDrillingInfos() {
        return this.drillingInfos;
    }
    
    public void setDrillingInfos(Set<DrillingInfo> drillingInfos) {
        this.drillingInfos = drillingInfos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="well")
    public Set<FluidLevelMeasurements> getFluidLevelMeasurementses() {
        return this.fluidLevelMeasurementses;
    }
    
    public void setFluidLevelMeasurementses(Set<FluidLevelMeasurements> fluidLevelMeasurementses) {
        this.fluidLevelMeasurementses = fluidLevelMeasurementses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="well")
    public Set<LabMesurement> getLabMesurements() {
        return this.labMesurements;
    }
    
    public void setLabMesurements(Set<LabMesurement> labMesurements) {
        this.labMesurements = labMesurements;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="well")
    public Set<WellGeneralInfo> getWellGeneralInfos() {
        return this.wellGeneralInfos;
    }
    
    public void setWellGeneralInfos(Set<WellGeneralInfo> wellGeneralInfos) {
        this.wellGeneralInfos = wellGeneralInfos;
    }




}


