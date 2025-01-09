package com.arthenyo.rotapro_backend.dto;

public class DriverTotalCostDTO {

    private String driverName;
    private Double totalCost;

    public DriverTotalCostDTO() {
    }

    public DriverTotalCostDTO(String driverName, Double totalCost) {
        this.driverName = driverName;
        this.totalCost = totalCost;
    }

    public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}

