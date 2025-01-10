package com.arthenyo.rotapro_backend.model.model_postgresql;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_filial")
public class BranchPostgresql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo")
    private Integer code;
    @Column(name = "razao_social")
    private String socialReason;
    @Column(name = "cnpj")
    private String cnpj;

    public BranchPostgresql() {
    }

    public BranchPostgresql(Long id, Integer code, String socialReason, String cnpj) {
        this.id = id;
        this.code = code;
        this.socialReason = socialReason;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchPostgresql that = (BranchPostgresql) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
