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
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "MARCA")
    private String mark;
    @Column(name = "TIPOVEICULO")
    private String typeVehicle;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
