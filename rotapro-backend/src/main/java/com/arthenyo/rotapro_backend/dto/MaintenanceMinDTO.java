package com.arthenyo.rotapro_backend.dto;

import com.arthenyo.rotapro_backend.model.model_postgresql.MaintenancePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.MaintenanceType;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusMaintenance;

import java.time.LocalDate;

public class MaintenanceMinDTO {
    private Long id;
    private String vehicle;
    private LocalDate maintenanceDate;
    private MaintenanceType maintenanceType;
    private String description;
    private Double totalCost;
    private StatusMaintenance status;

    public MaintenanceMinDTO() {
    }

    public MaintenanceMinDTO(MaintenancePostgresql entity) {
        id = entity.getId();
        vehicle = entity.getVehicle().getDescription();
        maintenanceDate = entity.getMaintenanceDate();
        maintenanceType = entity.getMaintenanceType();
        description = entity.getDescription();
        totalCost = entity.getTotalCost();
        status = entity.getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public MaintenanceType getMaintenanceType() {
        return maintenanceType;
    }

    public String getDescription() {
        return description;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public StatusMaintenance getStatus() {
        return status;
    }
}
