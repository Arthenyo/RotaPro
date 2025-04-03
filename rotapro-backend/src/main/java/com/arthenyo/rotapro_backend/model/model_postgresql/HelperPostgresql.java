package com.arthenyo.rotapro_backend.model.model_postgresql;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_ajudantes")
public class HelperPostgresql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "MATRICULA")
    private Integer registration;
    @Column(name = "NOME")
    private String name;
    @Column(name = "CODSETOR")
    private Integer sector;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FONE")
    private String fone;
    public HelperPostgresql() {
    }

    public HelperPostgresql(Long id, Integer registration, String name, Integer sector, String email, String fone) {
        this.id = id;
        this.registration = registration;
        this.name = name;
        this.sector = sector;
        this.email = email;
        this.fone = fone;
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

    public Integer getSector() {
        return sector;
    }

    public void setSector(Integer sector) {
        this.sector = sector;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelperPostgresql that = (HelperPostgresql) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
