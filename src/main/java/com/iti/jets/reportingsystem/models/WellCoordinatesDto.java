package com.iti.jets.reportingsystem.models;

public class WellCoordinatesDto {
    private Double xcord;
    private Double ycord;
    private String wellName;

    public WellCoordinatesDto() {
    }

    public WellCoordinatesDto(Double xcord, Double ycord, String wellName) {
        this.xcord = xcord;
        this.ycord = ycord;
        this.wellName = wellName;
    }

    public Double getXcord() {
        return xcord;
    }

    public Double getYcord() {
        return ycord;
    }

    public String getWellName() {
        return wellName;
    }

    public void setXcord(Double xcord) {
        this.xcord = xcord;
    }

    public void setYcord(Double ycord) {
        this.ycord = ycord;
    }

    public void setWellName(String wellName) {
        this.wellName = wellName;
    }
}
