package com.arthenyo.rotapro_backend.model.model_oracle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class HelperOracle {
    @Id
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

    public Integer getRegistration() {
        return registration;
    }

    public String getName() {
        return name;
    }

    public Integer getSector() {
        return sector;
    }

    public String getEmail() {
        return email;
    }

    public String getFone() {
        return fone;
    }
}
