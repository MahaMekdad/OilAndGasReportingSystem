package com.iti.jets.reportingsystem.entities;
// Generated Jun 2, 2021, 9:20:41 PM by Hibernate Tools 6.0.0.Alpha2


import javax.persistence.*;
import java.util.Date;

/**
 * IntervalsInfo generated by hbm2java
 */
@Entity
@Table(name="intervals_info"
    ,catalog="reportingsystem"
)
public class IntervalsInfo  implements java.io.Serializable {


     private int id;
     private Well well;
     private String drivingMechanism;
     private String zones;
     private String interval;
     private Double from;
     private Double to;
     private Double length;
     private Double pr;
     private Double tr;
     private String status;
     private Date startDate;
     private Date endDate;
     private Double pi;
     private Double mobility;
     private String fluidType;
     private Double api;
     private String notes;

    public IntervalsInfo() {
    }

	
    public IntervalsInfo(int id, Well well) {
        this.id = id;
        this.well = well;
    }
    public IntervalsInfo(int id, Well well, String drivingMechanism, String zones, String interval, Double from, Double to, Double length, Double pr, Double tr, String status, Date startDate, Date endDate, Double pi, Double mobility, String fluidType, Double api, String notes) {
       this.id = id;
       this.well = well;
       this.drivingMechanism = drivingMechanism;
       this.zones = zones;
       this.interval = interval;
       this.from = from;
       this.to = to;
       this.length = length;
       this.pr = pr;
       this.tr = tr;
       this.status = status;
       this.startDate = startDate;
       this.endDate = endDate;
       this.pi = pi;
       this.mobility = mobility;
       this.fluidType = fluidType;
       this.api = api;
       this.notes = notes;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
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

    
    @Column(name="driving_mechanism", length=16)
    public String getDrivingMechanism() {
        return this.drivingMechanism;
    }
    
    public void setDrivingMechanism(String drivingMechanism) {
        this.drivingMechanism = drivingMechanism;
    }

    
    @Column(name="zones", length=200)
    public String getZones() {
        return this.zones;
    }
    
    public void setZones(String zones) {
        this.zones = zones;
    }

    
    @Column(name="interval", length=200)
    public String getInterval() {
        return this.interval;
    }
    
    public void setInterval(String interval) {
        this.interval = interval;
    }

    
    @Column(name="from", precision=22, scale=0)
    public Double getFrom() {
        return this.from;
    }
    
    public void setFrom(Double from) {
        this.from = from;
    }

    
    @Column(name="to", precision=22, scale=0)
    public Double getTo() {
        return this.to;
    }
    
    public void setTo(Double to) {
        this.to = to;
    }

    
    @Column(name="length", precision=22, scale=0)
    public Double getLength() {
        return this.length;
    }
    
    public void setLength(Double length) {
        this.length = length;
    }

    
    @Column(name="pr", precision=22, scale=0)
    public Double getPr() {
        return this.pr;
    }
    
    public void setPr(Double pr) {
        this.pr = pr;
    }

    
    @Column(name="tr", precision=22, scale=0)
    public Double getTr() {
        return this.tr;
    }
    
    public void setTr(Double tr) {
        this.tr = tr;
    }

    
    @Column(name="status", length=6)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="start_date", length=10)
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="end_date", length=10)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    @Column(name="pi", precision=22, scale=0)
    public Double getPi() {
        return this.pi;
    }
    
    public void setPi(Double pi) {
        this.pi = pi;
    }

    
    @Column(name="mobility", precision=22, scale=0)
    public Double getMobility() {
        return this.mobility;
    }
    
    public void setMobility(Double mobility) {
        this.mobility = mobility;
    }

    
    @Column(name="fluidType", length=100)
    public String getFluidType() {
        return this.fluidType;
    }
    
    public void setFluidType(String fluidType) {
        this.fluidType = fluidType;
    }

    
    @Column(name="api", precision=22, scale=0)
    public Double getApi() {
        return this.api;
    }
    
    public void setApi(Double api) {
        this.api = api;
    }

    
    @Column(name="notes", length=5000)
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }




}


