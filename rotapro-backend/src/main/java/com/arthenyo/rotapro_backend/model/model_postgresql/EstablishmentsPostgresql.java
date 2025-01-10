package com.arthenyo.rotapro_backend.model.model_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.enums.TypeEstablishments;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_estabelecimento")
public class EstablishmentsPostgresql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    @Enumerated(EnumType.STRING)
    private TypeEstablishments type;

    public EstablishmentsPostgresql() {
    }

    public EstablishmentsPostgresql(Long id, String name, String address, String city, String state, TypeEstablishments type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public TypeEstablishments getType() {
        return type;
    }

    public void setType(TypeEstablishments type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstablishmentsPostgresql that = (EstablishmentsPostgresql) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
