package com.arthenyo.rotapro_backend.model.model_oracle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class RouteOracle {
    @Column(name = "CODCLI")
    private String codCli;
    @Column(name = "NUMMDFE")
    private Integer numMdfe;
    @Column(name = "SITUACAOMDFE")
    private Integer situacaoMdfe;
    @Id
    @Column(name = "NUMCAR")
    private Integer numCar;
    @Column(name = "DTSAIDA")
    private LocalDate dtSaida;
    @Column(name = "CODMOTORISTA")
    private Long codMotorista;
    @Column(name = "CODVEICULO")
    private Long codVeiculo;
    @Column(name = "TOTALPESO")
    private Double totalPeso;

    public List<Long> getCodCliAsList() {
        return Arrays.stream(codCli.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    public String getCodCli() {
        return codCli;
    }

    public void setCodCli(String codCli) {
        this.codCli = codCli;
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

    public Integer getNumCar() {
        return numCar;
    }

    public void setNumCar(Integer numCar) {
        this.numCar = numCar;
    }

    public LocalDate getDtSaida() {
        return dtSaida;
    }

    public void setDtSaida(LocalDate dtSaida) {
        this.dtSaida = dtSaida;
    }

    public Long getCodMotorista() {
        return codMotorista;
    }

    public void setCodMotorista(Long codMotorista) {
        this.codMotorista = codMotorista;
    }

    public Long getCodVeiculo() {
        return codVeiculo;
    }

    public void setCodVeiculo(Long codVeiculo) {
        this.codVeiculo = codVeiculo;
    }

    public Double getTotalPeso() {
        return totalPeso;
    }

    public void setTotalPeso(Double totalPeso) {
        this.totalPeso = totalPeso;
    }
}
