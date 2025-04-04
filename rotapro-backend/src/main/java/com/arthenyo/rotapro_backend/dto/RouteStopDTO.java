package com.arthenyo.rotapro_backend.dto;

import com.arthenyo.rotapro_backend.model.model_postgresql.RouteStopPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StopType;

import java.time.LocalDateTime;

public class RouteStopDTO {
    private Long id;
    private Long routeId;
    private Long establishmentId;
    private StopType stopType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double cost;

    public RouteStopDTO() {
    }

    public RouteStopDTO(RouteStopPostgresql entity) {
        this.id = entity.getId();
        this.routeId = (entity.getRoute() != null) ? entity.getRoute().getId() : null;
        this.establishmentId = (entity.getEstablishment() != null) ? entity.getEstablishment().getId() : null;
        this.stopType = entity.getStopType();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.cost = entity.getCost();
    }

    public Long getId() {
        return id;
    }

    public Long getRouteId() {
        return routeId;
    }

    public Long getEstablishmentId() {
        return establishmentId;
    }

    public StopType getStopType() {
        return stopType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Double getCost() {
        return cost;
    }
}
