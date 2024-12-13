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
    private String descricao;
    @Column(name = "MARCA")
    private String mark;
    @Column(name = "TIPO_VEICULO")
    private String typeVehicle;
    @Column(name = "COR")
    private String color;
    @Column(name = "PESOCARGAKG")
    private Double capacity;
    @Column(name = "QTEIXOS")
    private Integer qntEixos;
    @Column(name = "QTRODAS")
    private Integer qntRodas;
    @Column(name = "QTLITROS")
    private Double qntLitros;
    private Boolean status;
    @Column(name = "Disponibilidades")
    @Enumerated(EnumType.STRING)
    private Availabilities availabilities;

    public VehiclePostgresql() {
    }

    public VehiclePostgresql(Long id, Integer codVehicle, String plate, String descricao, String mark, String typeVehicle, String color, Double capacity, Integer qntEixos, Integer qntRodas, Double qntLitros, Boolean status, Availabilities availabilities) {
        this.id = id;
        this.codVehicle = codVehicle;
        this.plate = plate;
        this.descricao = descricao;
        this.mark = mark;
        this.typeVehicle = typeVehicle;
        this.color = color;
        this.capacity = capacity;
        this.qntEixos = qntEixos;
        this.qntRodas = qntRodas;
        this.qntLitros = qntLitros;
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

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Integer getQntEixos() {
        return qntEixos;
    }

    public void setQntEixos(Integer qntEixos) {
        this.qntEixos = qntEixos;
    }

    public Integer getQntRodas() {
        return qntRodas;
    }

    public void setQntRodas(Integer qntRodas) {
        this.qntRodas = qntRodas;
    }

    public Double getQntLitros() {
        return qntLitros;
    }

    public void setQntLitros(Double qntLitros) {
        this.qntLitros = qntLitros;
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
