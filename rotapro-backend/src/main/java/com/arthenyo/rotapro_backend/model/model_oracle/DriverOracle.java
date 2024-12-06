package com.arthenyo.rotapro_backend.model.model_oracle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class DriverOracle {
    @Id
    @Column(name = "MATRICULA")
    private Integer registration;
    @Column(name = "NOME")
    private String name;
    @Column(name = "CNH")
    private String cnh;
    @Column(name = "CATEGORIACNH", nullable = false)
    private String categoryCNH;
    @Column(name = "DTVALIDADECNH",nullable = false)
    private LocalDateTime validityCNH;

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
}
