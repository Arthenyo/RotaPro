package com.arthenyo.rotapro_backend.model.model_postgresql.enums;

public enum Availabilities {
    AVAILABLE("DISPONIVEL"),
    IN_LOAD("EM_ROTA"),
    UNAVAILABLE("INDISPONIVEL");
    private String description;
    Availabilities(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
