package com.arthenyo.rotapro_backend.dto;

import com.arthenyo.rotapro_backend.model.model_postgresql.PartCostPostgresql;

public class PartCostDTO {
    private Long id;
    private String partName;
    private Double cost;

    public PartCostDTO() {
    }

    public PartCostDTO(PartCostPostgresql entity) {
        id = entity.getId();
        partName = entity.getPartName();
        cost = entity.getCost();
    }

    public Long getId() {
        return id;
    }

    public String getPartName() {
        return partName;
    }

    public Double getCost() {
        return cost;
    }
}
