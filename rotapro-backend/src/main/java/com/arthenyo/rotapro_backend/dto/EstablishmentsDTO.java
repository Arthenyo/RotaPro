package com.arthenyo.rotapro_backend.dto;

import com.arthenyo.rotapro_backend.model.model_postgresql.EstablishmentsPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.TypeEstablishments;

public class EstablishmentsDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private TypeEstablishments type;

    public EstablishmentsDTO(Long id, String name, String address, String city, String state, TypeEstablishments type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.type = type;
    }
    public EstablishmentsDTO(EstablishmentsPostgresql entity) {
        id = entity.getId();
        name = entity.getName();
        address = entity.getAddress();
        city = entity.getCity();
        state = entity.getState();
        type = entity.getType();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public TypeEstablishments getType() {
        return type;
    }
}
