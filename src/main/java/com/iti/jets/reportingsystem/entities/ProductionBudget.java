package com.iti.jets.reportingsystem.entities;
// Generated Jun 7, 2021, 4:22:33 AM by Hibernate Tools 6.0.0.Alpha2


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ProductionBudget generated by hbm2java
 */
@Entity
@Table(name="production_budget"
    ,catalog="reportingsystem"
)
public class ProductionBudget  implements java.io.Serializable {


     private Integer id;
     private Date productionDate;
     private Double meleiha;
     private Double aghar;
     private Double eastKanays;
     private Double zarif;
     private Double faras;
     private Double raml;
     private Double westernDesert;
     private Double ashrafi;
     private Double agibaOil;
     private Double salesGas;
     private Double agibaBoe;

    public ProductionBudget() {
    }

    public ProductionBudget(Date productionDate, Double meleiha, Double aghar, Double eastKanays, Double zarif, Double faras, Double raml, Double westernDesert, Double ashrafi, Double agibaOil, Double salesGas, Double agibaBoe) {
       this.productionDate = productionDate;
       this.meleiha = meleiha;
       this.aghar = aghar;
       this.eastKanays = eastKanays;
       this.zarif = zarif;
       this.faras = faras;
       this.raml = raml;
       this.westernDesert = westernDesert;
       this.ashrafi = ashrafi;
       this.agibaOil = agibaOil;
       this.salesGas = salesGas;
       this.agibaBoe = agibaBoe;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="production_date", length=10)
    public Date getProductionDate() {
        return this.productionDate;
    }
    
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    
    @Column(name="meleiha", precision=22, scale=0)
    public Double getMeleiha() {
        return this.meleiha;
    }
    
    public void setMeleiha(Double meleiha) {
        this.meleiha = meleiha;
    }

    
    @Column(name="aghar", precision=22, scale=0)
    public Double getAghar() {
        return this.aghar;
    }
    
    public void setAghar(Double aghar) {
        this.aghar = aghar;
    }

    
    @Column(name="east_kanays", precision=22, scale=0)
    public Double getEastKanays() {
        return this.eastKanays;
    }
    
    public void setEastKanays(Double eastKanays) {
        this.eastKanays = eastKanays;
    }

    
    @Column(name="zarif", precision=22, scale=0)
    public Double getZarif() {
        return this.zarif;
    }
    
    public void setZarif(Double zarif) {
        this.zarif = zarif;
    }

    
    @Column(name="faras", precision=22, scale=0)
    public Double getFaras() {
        return this.faras;
    }
    
    public void setFaras(Double faras) {
        this.faras = faras;
    }

    
    @Column(name="raml", precision=22, scale=0)
    public Double getRaml() {
        return this.raml;
    }
    
    public void setRaml(Double raml) {
        this.raml = raml;
    }

    
    @Column(name="western_desert", precision=22, scale=0)
    public Double getWesternDesert() {
        return this.westernDesert;
    }
    
    public void setWesternDesert(Double westernDesert) {
        this.westernDesert = westernDesert;
    }

    
    @Column(name="ashrafi", precision=22, scale=0)
    public Double getAshrafi() {
        return this.ashrafi;
    }
    
    public void setAshrafi(Double ashrafi) {
        this.ashrafi = ashrafi;
    }

    
    @Column(name="agiba_oil", precision=22, scale=0)
    public Double getAgibaOil() {
        return this.agibaOil;
    }
    
    public void setAgibaOil(Double agibaOil) {
        this.agibaOil = agibaOil;
    }

    
    @Column(name="sales_gas", precision=22, scale=0)
    public Double getSalesGas() {
        return this.salesGas;
    }
    
    public void setSalesGas(Double salesGas) {
        this.salesGas = salesGas;
    }

    
    @Column(name="agiba_boe", precision=22, scale=0)
    public Double getAgibaBoe() {
        return this.agibaBoe;
    }
    
    public void setAgibaBoe(Double agibaBoe) {
        this.agibaBoe = agibaBoe;
    }




}


