package com.arthenyo.rotapro_backend.model.model_postgresql;

import com.arthenyo.rotapro_backend.dto.FuelSupplyDetailsDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StopType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_rota_parada")
public class RouteStopPostgresql {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rota_id", nullable = false)
    private RoutePostgresql route;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private EstablishmentsPostgresql establishment;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_parada", nullable = false)
    private StopType stopType;

    @Column(name = "hora_inicio")
    private LocalDateTime startTime;

    @Column(name = "hora_fim")
    private LocalDateTime endTime;

    @Column(name = "custo", nullable = false)
    private Double cost;
    @Transient
    private FuelSupplyPostgresql fuelSupply;

    public RouteStopPostgresql() {
    }

    public RouteStopPostgresql(Long id, RoutePostgresql route, EstablishmentsPostgresql establishment, StopType stopType, LocalDateTime startTime, LocalDateTime endTime, Double cost) {
        this.id = id;
        this.route = route;
        this.establishment = establishment;
        this.stopType = stopType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public RoutePostgresql getRoute() {
        return route;
    }

    public void setRoute(RoutePostgresql route) {
        this.route = route;
    }

    public EstablishmentsPostgresql getEstablishment() {
        return establishment;
    }

    public void setEstablishment(EstablishmentsPostgresql establishment) {
        this.establishment = establishment;
    }

    public StopType getStopType() {
        return stopType;
    }

    public void setStopType(StopType stopType) {
        this.stopType = stopType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteStopPostgresql that = (RouteStopPostgresql) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
