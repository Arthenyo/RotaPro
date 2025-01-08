package com.arthenyo.rotapro_backend.dto;

import com.arthenyo.rotapro_backend.model.model_postgresql.MaintenancePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.PartCostPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.MaintenanceType;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusMaintenance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDTO {
    private Long id;
    private String vehicle;
    private LocalDate maintenanceDate;
    private MaintenanceType maintenanceType;
    private String description;
    private Double totalCost;
    private String serviceProvider;
    private String responsiblePerson;
    private List<PartCostDTO> partCosts = new ArrayList<>();
    private StatusMaintenance status;
    private LocalDate nextMaintenanceDate;
    private Integer odometerReading;
    private Double fuelLevel;
    private String invoiceNumber;
    private String maintenanceReport;
    private Boolean approved;
    private String approvalComments;

    public MaintenanceDTO() {
    }

    public MaintenanceDTO(MaintenancePostgresql entity) {
        id = entity.getId();
        vehicle = entity.getVehicle().getDescription();
        maintenanceDate = entity.getMaintenanceDate();
        maintenanceType = entity.getMaintenanceType();
        description = entity.getDescription();
        totalCost = entity.getTotalCost();
        serviceProvider = entity.getServiceProvider();
        responsiblePerson = entity.getResponsiblePerson();
        status = entity.getStatus();
        nextMaintenanceDate = entity.getNextMaintenanceDate();
        odometerReading = entity.getOdometerReading();
        fuelLevel = entity.getFuelLevel();
        invoiceNumber = entity.getInvoiceNumber();
        maintenanceReport = entity.getMaintenanceReport();
        approved = entity.getApproved();
        approvalComments = entity.getApprovalComments();
        for(PartCostPostgresql partCost: entity.getPartCosts()){
            partCosts.add(new PartCostDTO(partCost));
        }
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

    public String getServiceProvider() {
        return serviceProvider;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public List<PartCostDTO> getPartCosts() {
        return partCosts;
    }

    public StatusMaintenance getStatus() {
        return status;
    }

    public LocalDate getNextMaintenanceDate() {
        return nextMaintenanceDate;
    }

    public Integer getOdometerReading() {
        return odometerReading;
    }

    public Double getFuelLevel() {
        return fuelLevel;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getMaintenanceReport() {
        return maintenanceReport;
    }

    public Boolean getApproved() {
        return approved;
    }

    public String getApprovalComments() {
        return approvalComments;
    }
}
