package com.arthenyo.rotapro_backend.dto;

import com.arthenyo.rotapro_backend.model.model_postgresql.*;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusRouter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RouteDTO {
    private Long id;

    private Integer driver;

    private Integer vehicle;

    private List<Integer> clients = new ArrayList<>();
    private List<Integer> helpers = new ArrayList<>();

    private Integer charge;
    private Integer numMdfe;
    private Integer situacaoMdfe;
    private Double totalWeight;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer kminicial;
    private Integer kmfinal;
    private Integer totalKm;

    @Enumerated(EnumType.STRING)
    private StatusRouter status;

    private List<RouteStopDTO> stops = new ArrayList<>(); // Lista de paradas

    public RouteDTO() {
    }

    public RouteDTO(RoutePostgresql entity) {
        id = entity.getId();
        driver = entity.getDriver().getRegistration();
        vehicle = entity.getVehicle().getCodVehicle();
        charge = entity.getCharge();
        numMdfe = entity.getNumMdfe();
        situacaoMdfe = entity.getSituacaoMdfe();
        totalWeight = entity.getTotalWeight();
        startDate = entity.getStartDate();
        endDate = entity.getEndDate();
        kminicial = entity.getKminicial();
        kmfinal = entity.getKmfinal();
        totalKm = entity.getTotalKm();
        status = entity.getStatus();

        for (ClientPostgresql client : entity.getClients()) {
            clients.add(client.getCodClient());
        }
        for (HelperPostgresql helper : entity.getHelpers()) {
            helpers.add(helper.getRegistration());
        }

        for (RouteStopPostgresql stop : entity.getStops()) {
            stops.add(new RouteStopDTO(stop));
        }
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public Integer getDriver() {
        return driver;
    }

    public Integer getVehicle() {
        return vehicle;
    }

    public List<Integer> getClients() {
        return clients;
    }

    public List<Integer> getHelpers() {
        return helpers;
    }

    public Integer getCharge() {
        return charge;
    }

    public Integer getNumMdfe() {
        return numMdfe;
    }

    public Integer getSituacaoMdfe() {
        return situacaoMdfe;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getKminicial() {
        return kminicial;
    }

    public Integer getKmfinal() {
        return kmfinal;
    }

    public Integer getTotalKm() {
        return totalKm;
    }

    public StatusRouter getStatus() {
        return status;
    }

    public List<RouteStopDTO> getStops() {
        return stops;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDriver(Integer driver) {
        this.driver = driver;
    }

    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    public void setClients(List<Integer> clients) {
        this.clients = clients;
    }

    public void setHelpers(List<Integer> helpers) {
        this.helpers = helpers;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public void setNumMdfe(Integer numMdfe) {
        this.numMdfe = numMdfe;
    }

    public void setSituacaoMdfe(Integer situacaoMdfe) {
        this.situacaoMdfe = situacaoMdfe;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setKminicial(Integer kminicial) {
        this.kminicial = kminicial;
    }

    public void setKmfinal(Integer kmfinal) {
        this.kmfinal = kmfinal;
    }

    public void setTotalKm(Integer totalKm) {
        this.totalKm = totalKm;
    }

    public void setStatus(StatusRouter status) {
        this.status = status;
    }

    public void setStops(List<RouteStopDTO> stops) {
        this.stops = stops;
    }
}
