package com.iti.jets.reportingsystem.models;

import java.io.Serializable;
import java.util.Date;

public class ProductionBudgetModel implements Serializable {
    private int id;
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

    public ProductionBudgetModel(Date productionDate, Double meleiha, Double aghar, Double eastKanays, Double zarif, Double faras, Double raml, Double westernDesert, Double ashrafi, Double agibaOil, Double salesGas, Double agibaBoe) {
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

    public ProductionBudgetModel(int id, Date productionDate, Double meleiha, Double aghar, Double eastKanays, Double zarif, Double faras, Double raml, Double westernDesert, Double ashrafi, Double agibaOil, Double salesGas, Double agibaBoe) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Double getMeleiha() {
        return meleiha;
    }

    public void setMeleiha(Double meleiha) {
        this.meleiha = meleiha;
    }

    public Double getAghar() {
        return aghar;
    }

    public void setAghar(Double aghar) {
        this.aghar = aghar;
    }

    public Double getEastKanays() {
        return eastKanays;
    }

    public void setEastKanays(Double eastKanays) {
        this.eastKanays = eastKanays;
    }

    public Double getZarif() {
        return zarif;
    }

    public void setZarif(Double zarif) {
        this.zarif = zarif;
    }

    public Double getFaras() {
        return faras;
    }

    public void setFaras(Double faras) {
        this.faras = faras;
    }

    public Double getRaml() {
        return raml;
    }

    public void setRaml(Double raml) {
        this.raml = raml;
    }

    public Double getWesternDesert() {
        return westernDesert;
    }

    public void setWesternDesert(Double westernDesert) {
        this.westernDesert = westernDesert;
    }

    public Double getAshrafi() {
        return ashrafi;
    }

    public void setAshrafi(Double ashrafi) {
        this.ashrafi = ashrafi;
    }

    public Double getAgibaOil() {
        return agibaOil;
    }

    public void setAgibaOil(Double agibaOil) {
        this.agibaOil = agibaOil;
    }

    public Double getSalesGas() {
        return salesGas;
    }

    public void setSalesGas(Double salesGas) {
        this.salesGas = salesGas;
    }

    public Double getAgibaBoe() {
        return agibaBoe;
    }

    public void setAgibaBoe(Double agibaBoe) {
        this.agibaBoe = agibaBoe;
    }
}
