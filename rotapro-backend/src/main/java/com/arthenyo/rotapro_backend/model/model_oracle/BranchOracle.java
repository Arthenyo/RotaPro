package com.arthenyo.rotapro_backend.model.model_oracle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BranchOracle {
    @Id
    @Column(name = "CODIGO")
    private Integer code;
    @Column(name = "RAZAOSOCIAL")
    private String socialReason;
    @Column(name = "CGC")
    private String cnpj;

    public Integer getCode() {
        return code;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public String getCnpj() {
        return cnpj;
    }
}
