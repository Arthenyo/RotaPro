package com.arthenyo.rotapro_backend.dto;

import com.arthenyo.rotapro_backend.model.model_postgresql.DriverPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.FuelSupplyPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.RoutePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.VehiclePostgresql;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class FuelSupplyDTO {
    private Long id;
    private String driver;
    private String vehicle;
    private Integer route;
    private LocalDate fuelDate;
    private Integer odômetro;
    private Double liters;
    private Double totalCost;
    private Double pricePerLiter;
    private String gasStation;
    private String observations;

    public FuelSupplyDTO() {
    }

    public FuelSupplyDTO(FuelSupplyPostgresql entity) {
        id = entity.getId();
        driver = entity.getDriver().getName();
        vehicle = entity.getVehicle().getDescription();
        route = entity.getRoute().getCharge();
        fuelDate = entity.getFuelDate();
        odômetro = entity.getOdômetro();
        liters = entity.getLiters();
        totalCost = entity.getTotalCost();
        pricePerLiter = entity.getPricePerLiter();
        gasStation = entity.getGasStation();
        observations = entity.getObservations();
    }

    public Long getId() {
        return id;
    }

    public String getDriver() {
        return driver;
    }

    public String getVehicle() {
        return vehicle;
    }

    public Integer getRoute() {
        return route;
    }

    public LocalDate getFuelDate() {
        return fuelDate;
    }

    public Integer getOdômetro() {
        return odômetro;
    }

    public Double getLiters() {
        return liters;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public Double getPricePerLiter() {
        return pricePerLiter;
    }

    public String getGasStation() {
        return gasStation;
    }

    public String getObservations() {
        return observations;
    }
}
