package com.arthenyo.rotapro_backend.model.model_oracle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VehicleOracle {
    @Id
    @Column(name = "CODVEICULO")
    private Integer codVehicle;
    @Column(name = "PLACA")
    private String plate;
    @Column(name = "CHASSI")
    private String chassis;
    @Column(name = "MARCA")
    private String mark;
    @Column(name = "DESCRICAO")
    private String description;
    @Column(name = "TIPORODADO")
    private String typeWheeled;
    @Column(name = "PESOCARGAKG")
    private Integer loadWeightKg;
    @Column(name = "QTEIXOS")
    private Integer qtAxes;
    @Column(name = "QTRODAS")
    private Integer qntWheels;
    @Column(name = "QTLITROS")
    private Double qntLiters;
    @Column(name = "COMBUSTIVEL")
    private String fuel;
    @Column(name = "NOMEPROPRIETARIO")
    private String owner;
    @Column(name = "COR")
    private String color;

    public Integer getCodVehicle() {
        return codVehicle;
    }

    public void setCodVehicle(Integer codVehicle) {
        this.codVehicle = codVehicle;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeWheeled() {
        return typeWheeled;
    }

    public void setTypeWheeled(String typeWheeled) {
        this.typeWheeled = typeWheeled;
    }

    public Integer getLoadWeightKg() {
        return loadWeightKg;
    }

    public void setLoadWeightKg(Integer loadWeightKg) {
        this.loadWeightKg = loadWeightKg;
    }

    public Integer getQtAxes() {
        return qtAxes;
    }

    public void setQtAxes(Integer qtAxes) {
        this.qtAxes = qtAxes;
    }

    public Integer getQntWheels() {
        return qntWheels;
    }

    public void setQntWheels(Integer qntWheels) {
        this.qntWheels = qntWheels;
    }

    public Double getQntLiters() {
        return qntLiters;
    }

    public void setQntLiters(Double qntLiters) {
        this.qntLiters = qntLiters;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
