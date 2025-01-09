package com.arthenyo.rotapro_backend.model.model_postgresql;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_abastecimento")
public class FuelSupplyPostgresql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Motorista_id")
    private DriverPostgresql driver;
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private VehiclePostgresql vehicle;
    @ManyToOne
    @JoinColumn(name = "rota_id")
    private RoutePostgresql route;
    @Column(name = "data_abastecimento", nullable = false)
    private LocalDate fuelDate;
    @Column(name = "odômetro ")
    private Integer odômetro;
    @Column(name = "litros ", nullable = false)
    private Double liters;
    @Column(name = "custo_total", nullable = false)
    private Double totalCost;
    @Column(name = "preço_por_litro ")
    private Double pricePerLiter;
    @Column(name = "Posto_de_gasolina")
    private String gasStation;
    @Column(columnDefinition = "TEXT", name = "observações ")
    private String observations;

    public FuelSupplyPostgresql() {
    }

    public FuelSupplyPostgresql(Long id, DriverPostgresql driver, VehiclePostgresql vehicle, RoutePostgresql route, LocalDate fuelDate, Integer odômetro, Double liters, Double totalCost, Double pricePerLiter, String gasStation, String observations) {
        this.id = id;
        this.driver = driver;
        this.vehicle = vehicle;
        this.route = route;
        this.fuelDate = fuelDate;
        this.odômetro = odômetro;
        this.liters = liters;
        this.totalCost = totalCost;
        this.pricePerLiter = pricePerLiter;
        this.gasStation = gasStation;
        this.observations = observations;
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

    public RoutePostgresql getRoute() {
        return route;
    }

    public void setRoute(RoutePostgresql route) {
        this.route = route;
    }

    public LocalDate getFuelDate() {
        return fuelDate;
    }

    public void setFuelDate(LocalDate fuelDate) {
        this.fuelDate = fuelDate;
    }

    public Integer getOdômetro() {
        return odômetro;
    }

    public void setOdômetro(Integer odômetro) {
        this.odômetro = odômetro;
    }

    public Double getLiters() {
        return liters;
    }

    public void setLiters(Double liters) {
        this.liters = liters;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(Double pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    public String getGasStation() {
        return gasStation;
    }

    public void setGasStation(String gasStation) {
        this.gasStation = gasStation;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelSupplyPostgresql that = (FuelSupplyPostgresql) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
