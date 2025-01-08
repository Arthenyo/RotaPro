package com.arthenyo.rotapro_backend.model.model_postgresql;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_custos_pecas")
public class PartCostPostgresql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_peca")
    private String partName;
    @Column(name = "custo")
    private Double cost;
    @ManyToOne
    @JoinColumn(name = "manutencao_id")
    private MaintenancePostgresql maintenance;

    public PartCostPostgresql() {
    }

    public PartCostPostgresql(Long id, String partName, Double cost, MaintenancePostgresql maintenance) {
        this.id = id;
        this.partName = partName;
        this.cost = cost;
        this.maintenance = maintenance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public MaintenancePostgresql getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(MaintenancePostgresql maintenance) {
        this.maintenance = maintenance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartCostPostgresql that = (PartCostPostgresql) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
