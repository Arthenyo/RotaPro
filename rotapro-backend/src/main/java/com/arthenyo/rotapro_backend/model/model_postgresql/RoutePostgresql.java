package com.arthenyo.rotapro_backend.model.model_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusRouter;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_route")
public class RoutePostgresql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private DriverPostgresql driver;

    @OneToOne
    private VehiclePostgresql vehicle;
    @ManyToMany
    private List<ClientPostgresql> clients = new ArrayList<>();
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

    public RoutePostgresql() {
    }

    public RoutePostgresql(Long id, DriverPostgresql driver, VehiclePostgresql vehicle, Integer charge, Integer numMdfe, Integer situacaoMdfe, Double totalWeight, LocalDate startDate, LocalDate endDate, Integer kminicial, Integer kmfinal, Integer totalKm, StatusRouter status) {
        this.id = id;
        this.driver = driver;
        this.vehicle = vehicle;
        this.charge = charge;
        this.numMdfe = numMdfe;
        this.situacaoMdfe = situacaoMdfe;
        this.totalWeight = totalWeight;
        this.startDate = startDate;
        this.endDate = endDate;
        this.kminicial = kminicial;
        this.kmfinal = kmfinal;
        this.totalKm = totalKm;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DriverPostgresql getDriver() {
        return driver;
    }

    public void setDriver(DriverPostgresql driver) {
        this.driver = driver;
    }

    public VehiclePostgresql getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehiclePostgresql vehicle) {
        this.vehicle = vehicle;
    }

    public List<ClientPostgresql> getClients() {
        return clients;
    }

    public void setClients(List<ClientPostgresql> clients) {
        this.clients = clients;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public Integer getNumMdfe() {
        return numMdfe;
    }

    public void setNumMdfe(Integer numMdfe) {
        this.numMdfe = numMdfe;
    }

    public Integer getSituacaoMdfe() {
        return situacaoMdfe;
    }

    public void setSituacaoMdfe(Integer situacaoMdfe) {
        this.situacaoMdfe = situacaoMdfe;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getKminicial() {
        return kminicial;
    }

    public void setKminicial(Integer kminicial) {
        this.kminicial = kminicial;
    }

    public Integer getKmfinal() {
        return kmfinal;
    }

    public void setKmfinal(Integer kmfinal) {
        this.kmfinal = kmfinal;
    }

    public Integer getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(Integer totalKm) {
        this.totalKm = totalKm;
    }

    public StatusRouter getStatus() {
        return status;
    }

    public void setStatus(StatusRouter status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutePostgresql that = (RoutePostgresql) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
