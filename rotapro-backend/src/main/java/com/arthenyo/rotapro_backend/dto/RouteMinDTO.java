package com.arthenyo.rotapro_backend.dto;

import com.arthenyo.rotapro_backend.model.model_postgresql.ClientPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.HelperPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.RoutePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.RouteStopPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusRouter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RouteMinDTO {
    private Long id;
    private Integer charge;
    private String driver;
    private String vehicle;
    private Long totalClientes;
    private String numnotas;
    private LocalDate startDate;
    @Enumerated(EnumType.STRING)
    private StatusRouter status;
    public RouteMinDTO() {
    }

    public RouteMinDTO(RoutePostgresql entity) {
        id = entity.getId();
        driver = entity.getDriver().getName();
        vehicle = entity.getVehicle().getPlate();
        charge = entity.getCharge();
        numnotas = entity.getNumnotas();
        totalClientes = entity.getTotalClientes();
        startDate = entity.getStartDate();
        status = entity.getStatus();
    }

    public Long getId() {
        return id;
    }

    public Integer getCharge() {
        return charge;
    }

    public String getDriver() {
        return driver;
    }

    public String getVehicle() {
        return vehicle;
    }

    public Long getTotalClientes() {
        return totalClientes;
    }

    public String getNumnotas() {
        return numnotas;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public StatusRouter getStatus() {
        return status;
    }
}
