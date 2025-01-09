package com.arthenyo.rotapro_backend.dto;

public class DriverSupplyCountDTO {

    private String driverName;
    private Long countSupplies;

    public DriverSupplyCountDTO() {
    }

    public DriverSupplyCountDTO(String driverName, Long countSupplies) {
        this.driverName = driverName;
        this.countSupplies = countSupplies;
    }

    public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Long getCountSupplies() {
        return countSupplies;
    }
    public void setCountSupplies(Long countSupplies) {
        this.countSupplies = countSupplies;
    }
}

