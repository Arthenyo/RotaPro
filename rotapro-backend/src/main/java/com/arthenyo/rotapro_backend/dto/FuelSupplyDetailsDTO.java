package com.arthenyo.rotapro_backend.dto;

public class FuelSupplyDetailsDTO {
    private Integer odometro;
    private Double liters;
    private Double totalCost;
    private Double pricePerLiter;
    private String observations;

    public FuelSupplyDetailsDTO(Integer odometro, Double liters, Double totalCost, Double pricePerLiter, String observations) {
        this.odometro = odometro;
        this.liters = liters;
        this.totalCost = totalCost;
        this.pricePerLiter = pricePerLiter;
        this.observations = observations;
    }

    public Integer getOdometro() {
        return odometro;
    }

    public void setOdometro(Integer odometro) {
        this.odometro = odometro;
    }

    public Double getLiters() {
        return liters;
    }

    public void setLiters(Double liters) {
        this.liters = liters;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(Double pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}

