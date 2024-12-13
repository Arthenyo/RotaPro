package com.arthenyo.rotapro_backend.model.model_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.enums.Availabilities;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_motorista")
public class DriverPostgresql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Matricula", nullable = false, unique = true)
    private Integer registration;
    @Column(name = "Nome")
    private String name;
    private String cnh;
    @Column(name = "CategoriaCNH")
    private String categoryCNH;
    @Column(name = "VAlidadeCNH")
    private LocalDateTime validityCNH;
    private Boolean status;
    @Column(name = "Disponibilidades")
    @Enumerated(EnumType.STRING)
    private Availabilities availabilities;

    public DriverPostgresql() {
    }

    public DriverPostgresql(Long id, Integer registration, String name, String cnh, String categoryCNH, LocalDateTime validityCNH, Boolean status, Availabilities availabilities) {
        this.id = id;
        this.registration = registration;
        this.name = name;
        this.cnh = cnh;
        this.categoryCNH = categoryCNH;
        this.validityCNH = validityCNH;
        this.status = status;
        this.availabilities = availabilities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getCategoryCNH() {
        return categoryCNH;
    }

    public void setCategoryCNH(String categoryCNH) {
        this.categoryCNH = categoryCNH;
    }

    public LocalDateTime getValidityCNH() {
        return validityCNH;
    }

    public void setValidityCNH(LocalDateTime validityCNH) {
        this.validityCNH = validityCNH;
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
        DriverPostgresql driverPostgresql = (DriverPostgresql) o;
        return Objects.equals(id, driverPostgresql.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
