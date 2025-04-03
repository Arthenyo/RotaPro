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

    private List<String> clients = new ArrayList<>();
    private Long totalClientes;
    private List<String> helpers = new ArrayList<>();

    private Integer charge;
    private String numnotas;
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

    private List<RouteStopDTO> stops = new ArrayList<>();

    public RouteDTO() {
    }

    public RouteDTO(RoutePostgresql entity) {
        id = entity.getId();
        driver = entity.getDriver().getRegistration();
        vehicle = entity.getVehicle().getCodVehicle();
        charge = entity.getCharge();
        numnotas = entity.getNumnotas();
        totalClientes = entity.getTotalClientes();
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
            clients.add(client.getName());
        }
        for (HelperPostgresql helper : entity.getHelpers()) {
            helpers.add(helper.getName());
        }

        for (RouteStopPostgresql stop : entity.getStops()) {
            stops.add(new RouteStopDTO(stop));
        }
    }

    public Long getId() {
        return id;
    }

    public Integer getDriver() {
        return driver;
    }

    public Integer getVehicle() {
        return vehicle;
    }

    public List<String> getClients() {
        return clients;
    }

    public Long getTotalClientes() {
        return totalClientes;
    }

    public List<String> getHelpers() {
        return helpers;
    }

    public Integer getCharge() {
        return charge;
    }

    public String getNumnotas() {
        return numnotas;
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
}
