package com.iti.jets.reportingsystem.entities;
// Generated Jun 2, 2021, 9:20:41 PM by Hibernate Tools 6.0.0.Alpha2


import javax.persistence.*;
import java.util.Date;

/**
 * DrillingInfo generated by hbm2java
 */
@Entity
@Table(name="drilling_info"
    ,catalog="reportingsystem"
)
public class DrillingInfo  implements java.io.Serializable {


     private int id;
     private Well well;
     private Date releaseDate;
     private String wellDescription;
     private String wellType;
     private String boreType;
     private Integer measuredDepth;
     private Integer tvdDepth;
     private Integer bbtp;
     private String productionGeneralInfo;

    public DrillingInfo() {
    }

	
    public DrillingInfo(int id, Well well) {
        this.id = id;
        this.well = well;
    }
    public DrillingInfo(int id, Well well, Date releaseDate, String wellDescription, String wellType, String boreType, Integer measuredDepth, Integer tvdDepth, Integer bbtp, String productionGeneralInfo) {
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

    @Temporal(TemporalType.DATE)
    @Column(name="release_date", length=10)
    public Date getReleaseDate() {
        return this.releaseDate;
    }
    
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    
    @Column(name="well_description", length=100)
    public String getWellDescription() {
        return this.wellDescription;
    }
    
    public void setWellDescription(String wellDescription) {
        this.wellDescription = wellDescription;
    }

    
    @Column(name="well_type", length=5000)
    public String getWellType() {
        return this.wellType;
    }
    
    public void setWellType(String wellType) {
        this.wellType = wellType;
    }

    
    @Column(name="bore_type", length=100)
    public String getBoreType() {
        return this.boreType;
    }
    
    public void setBoreType(String boreType) {
        this.boreType = boreType;
    }

    
    @Column(name="measured_depth")
    public Integer getMeasuredDepth() {
        return this.measuredDepth;
    }
    
    public void setMeasuredDepth(Integer measuredDepth) {
        this.measuredDepth = measuredDepth;
    }

    
    @Column(name="tvd_depth")
    public Integer getTvdDepth() {
        return this.tvdDepth;
    }
    
    public void setTvdDepth(Integer tvdDepth) {
        this.tvdDepth = tvdDepth;
    }

    
    @Column(name="bbtp")
    public Integer getBbtp() {
        return this.bbtp;
    }
    
    public void setBbtp(Integer bbtp) {
        this.bbtp = bbtp;
    }

    
    @Column(name="production_general_info", length=5000)
    public String getProductionGeneralInfo() {
        return this.productionGeneralInfo;
    }
    
    public void setProductionGeneralInfo(String productionGeneralInfo) {
        this.productionGeneralInfo = productionGeneralInfo;
    }




}


