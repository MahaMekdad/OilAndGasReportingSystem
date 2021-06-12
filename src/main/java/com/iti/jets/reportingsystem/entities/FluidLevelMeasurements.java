package com.iti.jets.reportingsystem.entities;
// Generated Jun 7, 2021, 4:22:33 AM by Hibernate Tools 6.0.0.Alpha2


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FluidLevelMeasurements generated by hbm2java
 */
@Entity
@Table(name="fluid_level_measurements"
    ,catalog="reportingsystem"
)
public class FluidLevelMeasurements  implements java.io.Serializable {

     private Integer id;
     private Well well;
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

    public FluidLevelMeasurements() {
    }

	
    public FluidLevelMeasurements(Well well) {
        this.well = well;
    }
    public FluidLevelMeasurements(Well well, Date date, String intervals, String flType, Double fluidLevel, Double pumpDepth, Long liqPercentage, Long pumpFillage, Double pumpSubmerge, Character card, String remarks) {
       this.well = well;
       this.date = date;
       this.intervals = intervals;
       this.flType = flType;
       this.fluidLevel = fluidLevel;
       this.pumpDepth = pumpDepth;
       this.liqPercentage = liqPercentage;
       this.pumpFillage = pumpFillage;
       this.pumpSubmerge = pumpSubmerge;
       this.card = card;
       this.remarks = remarks;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="well_id", nullable=false)
    public Well getWell() {
        return this.well;
    }
    
    public void setWell(Well well) {
        this.well = well;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="date", length=10)
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    
    @Column(name="intervals", length=200)
    public String getIntervals() {
        return this.intervals;
    }
    
    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }

    
    @Column(name="fl_type", length=7)
    public String getFlType() {
        return this.flType;
    }
    
    public void setFlType(String flType) {
        this.flType = flType;
    }

    
    @Column(name="fluid_level", precision=22, scale=0)
    public Double getFluidLevel() {
        return this.fluidLevel;
    }
    
    public void setFluidLevel(Double fluidLevel) {
        this.fluidLevel = fluidLevel;
    }

    
    @Column(name="pump_depth", precision=22, scale=0)
    public Double getPumpDepth() {
        return this.pumpDepth;
    }
    
    public void setPumpDepth(Double pumpDepth) {
        this.pumpDepth = pumpDepth;
    }

    
    @Column(name="liq_percentage", precision=10, scale=0)
    public Long getLiqPercentage() {
        return this.liqPercentage;
    }
    
    public void setLiqPercentage(Long liqPercentage) {
        this.liqPercentage = liqPercentage;
    }

    
    @Column(name="pump_fillage", precision=10, scale=0)
    public Long getPumpFillage() {
        return this.pumpFillage;
    }
    
    public void setPumpFillage(Long pumpFillage) {
        this.pumpFillage = pumpFillage;
    }

    
    @Column(name="pump_submerge", precision=22, scale=0)
    public Double getPumpSubmerge() {
        return this.pumpSubmerge;
    }
    
    public void setPumpSubmerge(Double pumpSubmerge) {
        this.pumpSubmerge = pumpSubmerge;
    }

    
    @Column(name="card", length=1)
    public Character getCard() {
        return this.card;
    }
    
    public void setCard(Character card) {
        this.card = card;
    }

    
    @Column(name="remarks", length=2000)
    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }




}


