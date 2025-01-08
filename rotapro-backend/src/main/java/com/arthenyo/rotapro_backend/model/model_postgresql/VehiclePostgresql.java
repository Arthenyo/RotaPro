package com.arthenyo.rotapro_backend.model.model_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.enums.Availabilities;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_veiculo")
public class VehiclePostgresql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CODVEICULO")
    private Integer codVehicle;
    @Column(name = "PLACA")
    private String plate;
    @Column(name = "DESCRICAO")
    private String description;
    @Column(name = "MARCA")
    private String mark;
    @Column(name = "TIPO_VEICULO")
    private String typeVehicle;
    @Column(name = "COR")
    private String color;
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
    private Boolean status;
    @Column(name = "Disponibilidades")
    @Enumerated(EnumType.STRING)
    private Availabilities availabilities;

    public VehiclePostgresql() {
    }

    public VehiclePostgresql(Long id, Integer codVehicle, String plate, String description, String mark, String typeVehicle, String color, Integer loadWeightKg, Integer qtAxes, Integer qntWheels, Double qntLiters, String fuel, String owner, Boolean status, Availabilities availabilities) {
        this.id = id;
        this.codVehicle = codVehicle;
        this.plate = plate;
        this.description = description;
        this.mark = mark;
        this.typeVehicle = typeVehicle;
        this.color = color;
        this.loadWeightKg = loadWeightKg;
        this.qtAxes = qtAxes;
        this.qntWheels = qntWheels;
        this.qntLiters = qntLiters;
        this.fuel = fuel;
        this.owner = owner;
        this.status = status;
        this.availabilities = availabilities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Availabilities getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Availabilities availabilities) {
        this.availabilities = availabilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehiclePostgresql vehiclePostgresql = (VehiclePostgresql) o;
        return Objects.equals(id, vehiclePostgresql.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
