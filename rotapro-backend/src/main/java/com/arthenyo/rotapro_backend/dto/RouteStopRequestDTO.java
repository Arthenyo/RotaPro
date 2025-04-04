package com.arthenyo.rotapro_backend.dto;

public class RouteStopRequestDTO {
    private RouteStopDTO routeStop;
    private FuelSupplyDetailsDTO fuelSupplyDetails;

    public RouteStopDTO getRouteStop() {
        return routeStop;
    }

    public void setRouteStop(RouteStopDTO routeStop) {
        this.routeStop = routeStop;
    }

    public FuelSupplyDetailsDTO getFuelSupplyDetails() {
        return fuelSupplyDetails;
    }

    public void setFuelSupplyDetails(FuelSupplyDetailsDTO fuelSupplyDetails) {
        this.fuelSupplyDetails = fuelSupplyDetails;
    }
}
