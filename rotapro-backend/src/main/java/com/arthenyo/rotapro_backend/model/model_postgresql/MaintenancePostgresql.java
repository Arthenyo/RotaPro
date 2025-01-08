package com.arthenyo.rotapro_backend.model.model_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.enums.MaintenanceType;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusMaintenance;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_manutencao")
public class MaintenancePostgresql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private VehiclePostgresql vehicle;

    @Column(name = "data_manutencao")
    private LocalDate maintenanceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_manutencao")
    private MaintenanceType maintenanceType;

    @Column(name = "descricao")
    private String description;

    @Column(name = "custo_total")
    private Double totalCost;

    @Column(name = "prestador_servico")
    private String serviceProvider;

    @Column(name = "responsavel")
    private String responsiblePerson;

    @OneToMany(mappedBy = "maintenance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartCostPostgresql> partCosts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusMaintenance status;

    @Column(name = "data_proxima_manutencao")
    private LocalDate nextMaintenanceDate;

    @Column(name = "odometro")
    private Integer odometerReading;

    @Column(name = "nivel_combustivel")
    private Double fuelLevel;

    @Column(name = "numero_nota_fiscal")
    private String invoiceNumber;

    @Column(name = "relatorio_manutencao")
    private String maintenanceReport;

    @Column(name = "aprovado")
    private Boolean approved;

    @Column(name = "comentarios_aprovacao")
    private String approvalComments;

    public MaintenancePostgresql() {
    }

    public MaintenancePostgresql(Long id, VehiclePostgresql vehicle, LocalDate maintenanceDate, MaintenanceType maintenanceType, String description, Double totalCost, String serviceProvider, String responsiblePerson, StatusMaintenance status, LocalDate nextMaintenanceDate, Integer odometerReading, Double fuelLevel, String invoiceNumber, String maintenanceReport, Boolean approved, String approvalComments) {
        this.id = id;
        this.vehicle = vehicle;
        this.maintenanceDate = maintenanceDate;
        this.maintenanceType = maintenanceType;
        this.description = description;
        this.totalCost = totalCost;
        this.serviceProvider = serviceProvider;
        this.responsiblePerson = responsiblePerson;
        this.status = status;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.odometerReading = odometerReading;
        this.fuelLevel = fuelLevel;
        this.invoiceNumber = invoiceNumber;
        this.maintenanceReport = maintenanceReport;
        this.approved = approved;
        this.approvalComments = approvalComments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehiclePostgresql getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehiclePostgresql vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public MaintenanceType getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(MaintenanceType maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public List<PartCostPostgresql> getPartCosts() {
        return partCosts;
    }

    public void setPartCosts(List<PartCostPostgresql> partCosts) {
        this.partCosts = partCosts;
    }

    public StatusMaintenance getStatus() {
        return status;
    }

    public void setStatus(StatusMaintenance status) {
        this.status = status;
    }

    public LocalDate getNextMaintenanceDate() {
        return nextMaintenanceDate;
    }

    public void setNextMaintenanceDate(LocalDate nextMaintenanceDate) {
        this.nextMaintenanceDate = nextMaintenanceDate;
    }

    public Integer getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(Integer odometerReading) {
        this.odometerReading = odometerReading;
    }

    public Double getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(Double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getMaintenanceReport() {
        return maintenanceReport;
    }

    public void setMaintenanceReport(String maintenanceReport) {
        this.maintenanceReport = maintenanceReport;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getApprovalComments() {
        return approvalComments;
    }

    public void setApprovalComments(String approvalComments) {
        this.approvalComments = approvalComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaintenancePostgresql that = (MaintenancePostgresql) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
