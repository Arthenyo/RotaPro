package com.arthenyo.rotapro_backend.customErro;

public class GasStationUsageDTO {

    private String gasStation;
    private Long countSupplies;

    public GasStationUsageDTO() {
    }

    public GasStationUsageDTO(String gasStation, Long countSupplies) {
        this.gasStation = gasStation;
        this.countSupplies = countSupplies;
    }

    public String getGasStation() {
        return gasStation;
    }

    public void setGasStation(String gasStation) {
        this.gasStation = gasStation;
    }

    public Long getCountSupplies() {
        return countSupplies;
    }

    public void setCountSupplies(Long countSupplies) {
        this.countSupplies = countSupplies;
    }
}

