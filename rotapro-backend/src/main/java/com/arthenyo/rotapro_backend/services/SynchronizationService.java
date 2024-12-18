package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.model.model_oracle.ClientOracle;
import com.arthenyo.rotapro_backend.model.model_oracle.DriverOracle;
import com.arthenyo.rotapro_backend.model.model_oracle.RouteOracle;
import com.arthenyo.rotapro_backend.model.model_oracle.VehicleOracle;
import com.arthenyo.rotapro_backend.model.model_postgresql.ClientPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.DriverPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.RoutePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.VehiclePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.Availabilities;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusRouter;
import com.arthenyo.rotapro_backend.repositories.repository_oracle.ClientOracleRepository;
import com.arthenyo.rotapro_backend.repositories.repository_oracle.DriverOracleRepository;
import com.arthenyo.rotapro_backend.repositories.repository_oracle.RouteOracleRepository;
import com.arthenyo.rotapro_backend.repositories.repository_oracle.VehicleOracleRepository;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.ClientPostgresqlRepository;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.DriverPostgresqlRepository;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.RoutePostgresqlRepository;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.VehiclePostgresqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SynchronizationService {

    @Autowired
    private ClientPostgresqlRepository clientPostgresqlRepository;
    @Autowired
    private ClientOracleRepository clientOracleRepository;
    @Autowired
    private DriverPostgresqlRepository driverPostgresqlRepository;
    @Autowired
    private DriverOracleRepository driverOracleRepository;
    @Autowired
    private VehiclePostgresqlRepository vehiclePostgresqlRepository;
    @Autowired
    private VehicleOracleRepository vehicleOracleRepository;
    @Autowired
    private RoutePostgresqlRepository routePostgresqlRepository;
    @Autowired
    private RouteOracleRepository routeOracleRepository;

    public String syncVehicles() {
        List<VehicleOracle> vehicleOracles = vehicleOracleRepository.getAllVehicles();
        if (vehicleOracles.isEmpty()) {
            return "No vehicles found in Oracle Database!";
        }

        List<VehiclePostgresql> vehiclesToSave = new ArrayList<>();

        for (VehicleOracle vehicleOracle : vehicleOracles) {
            Optional<VehiclePostgresql> existingVehicleOptional = vehiclePostgresqlRepository.findByCodVehicle(vehicleOracle.getCodVehicle());

            if (existingVehicleOptional.isPresent()) {
                VehiclePostgresql existingVehicle = existingVehicleOptional.get();
                boolean isUpdated = false;

                if (!existingVehicle.getPlate().equals(vehicleOracle.getPlate())) {
                    existingVehicle.setPlate(vehicleOracle.getPlate());
                    isUpdated = true;
                }

                if (!existingVehicle.getDescricao().equals(vehicleOracle.getDescricao())) {
                    existingVehicle.setDescricao(vehicleOracle.getDescricao());
                    isUpdated = true;
                }

                if (!existingVehicle.getMark().equals(vehicleOracle.getMark())) {
                    existingVehicle.setMark(vehicleOracle.getMark());
                    isUpdated = true;
                }

                if (!existingVehicle.getTypeVehicle().equals(vehicleOracle.getTypeVehicle())) {
                    existingVehicle.setTypeVehicle(vehicleOracle.getTypeVehicle());
                    isUpdated = true;
                }

                if (!existingVehicle.getColor().equals(vehicleOracle.getColor())) {
                    existingVehicle.setColor(vehicleOracle.getColor());
                    isUpdated = true;
                }

                if (isUpdated) {
                    vehiclesToSave.add(existingVehicle);
                }
            } else {

                VehiclePostgresql newVehicle = new VehiclePostgresql();
                newVehicle.setCodVehicle(vehicleOracle.getCodVehicle());
                newVehicle.setPlate(vehicleOracle.getPlate());
                newVehicle.setDescricao(vehicleOracle.getDescricao());
                newVehicle.setMark(vehicleOracle.getMark());
                newVehicle.setTypeVehicle(vehicleOracle.getTypeVehicle());
                newVehicle.setColor(vehicleOracle.getColor());
                newVehicle.setCapacity(null);
                newVehicle.setQntEixos(0);
                newVehicle.setQntRodas(0);
                newVehicle.setQntLitros(0.0);
                newVehicle.setStatus(true);
                newVehicle.setAvailabilities(Availabilities.AVAILABLE);
                vehiclesToSave.add(newVehicle);
            }
        }

        if (!vehiclesToSave.isEmpty()) {
            vehiclePostgresqlRepository.saveAll(vehiclesToSave);
            return "Vehicles synchronized successfully!";
        }

        return "No changes detected for vehicles.";
    }

    public String syncDrivers() {
        List<DriverOracle> driverOracleList = driverOracleRepository.getAllDriver();
        if (driverOracleList.isEmpty()) {
            return "No drivers found in Oracle Database!";
        }

        List<DriverPostgresql> driversToSave = new ArrayList<>();

        for (DriverOracle driverOracle : driverOracleList) {

            Optional<DriverPostgresql> existingDriverOptional = driverPostgresqlRepository.findByRegistration(driverOracle.getRegistration());

            if (existingDriverOptional.isPresent()) {
                DriverPostgresql existingDriver = existingDriverOptional.get();
                boolean isUpdated = false;

                if (!existingDriver.getName().equals(driverOracle.getName())) {
                    existingDriver.setName(driverOracle.getName());
                    isUpdated = true;
                }

                if (!existingDriver.getCnh().equals(driverOracle.getCnh())) {
                    existingDriver.setCnh(driverOracle.getCnh());
                    isUpdated = true;
                }

                if (!existingDriver.getCategoryCNH().equals(driverOracle.getCategoryCNH())) {
                    existingDriver.setCategoryCNH(driverOracle.getCategoryCNH());
                    isUpdated = true;
                }

                if (!existingDriver.getValidityCNH().equals(driverOracle.getValidityCNH())) {
                    existingDriver.setValidityCNH(driverOracle.getValidityCNH());
                    isUpdated = true;
                }

                if (isUpdated) {
                    driversToSave.add(existingDriver);
                }
            } else {
                DriverPostgresql newDriver = new DriverPostgresql();
                newDriver.setRegistration(driverOracle.getRegistration());
                newDriver.setName(driverOracle.getName());
                newDriver.setCnh(driverOracle.getCnh());
                newDriver.setCategoryCNH(driverOracle.getCategoryCNH());
                newDriver.setValidityCNH(driverOracle.getValidityCNH());
                newDriver.setStatus(Boolean.TRUE);
                newDriver.setAvailabilities(Availabilities.AVAILABLE);
                driversToSave.add(newDriver);
            }
        }

        if (!driversToSave.isEmpty()) {
            driverPostgresqlRepository.saveAll(driversToSave);
            return "Drivers synchronized successfully!";
        }

        return "No changes detected for drivers.";
    }

    public String syncClients() {
        List<ClientOracle> clientOracleList = clientOracleRepository.getAllClient();
        if (clientOracleList.isEmpty()) {
            return "No client found in Oracle Database!";
        }

        List<ClientPostgresql> clientToSave = new ArrayList<>();

        for (ClientOracle clientOracle : clientOracleList) {

            Optional<ClientPostgresql> existingClientOptional = clientPostgresqlRepository.findByCodClient(clientOracle.getCodcli());

            if (existingClientOptional.isPresent()) {
                ClientPostgresql existingClient = existingClientOptional.get();
                boolean isUpdated = false;

                if (!existingClient.getName().equals(clientOracle.getClient())) {
                    existingClient.setName(existingClient.getName());
                    isUpdated = true;
                }

                if (!existingClient.getAddress().equals(clientOracle.getAddress())) {
                    existingClient.setAddress(clientOracle.getAddress());
                    isUpdated = true;
                }

                if (!existingClient.getLatitude().equals(clientOracle.getLatitude())) {
                    existingClient.setLatitude(clientOracle.getLatitude());
                    isUpdated = true;
                }

                if (!existingClient.getLongitude().equals(clientOracle.getLongitude())) {
                    existingClient.setLongitude(clientOracle.getLongitude());
                    isUpdated = true;
                }

                if (isUpdated) {
                    clientToSave.add(existingClient);
                }
            } else {
                ClientPostgresql newClient = new ClientPostgresql();
                newClient.setCodClient(clientOracle.getCodcli());
                newClient.setName(clientOracle.getClient());
                newClient.setAddress(clientOracle.getAddress());
                newClient.setLatitude(clientOracle.getLatitude());
                newClient.setLongitude(clientOracle.getLongitude());
                clientToSave.add(newClient);
            }
        }

        if (!clientToSave.isEmpty()) {
            clientPostgresqlRepository.saveAll(clientToSave);
            return "Clients synchronized successfully!";
        }

        return "No changes detected for clients.";
    }
    public String syncRoutes() {
        List<RouteOracle> routesOracle = routeOracleRepository.findAllRoutesDataForToday();
        if (routesOracle.isEmpty()) {
            return "No routes found in Oracle Database!";
        }

        List<RoutePostgresql> routesToSave = new ArrayList<>();

        for (RouteOracle routeOracle : routesOracle) {
            Optional<RoutePostgresql> existingRouteOptional = routePostgresqlRepository.findByCharge(routeOracle.getNumCar());

            if (existingRouteOptional.isPresent()) {

                RoutePostgresql existingRoute = existingRouteOptional.get();
                boolean isUpdated = false;

                Optional<DriverPostgresql> driver = driverPostgresqlRepository.findById(routeOracle.getCodMotorista());
                driver.ifPresent(existingRoute::setDriver);

                Optional<VehiclePostgresql> vehicle = vehiclePostgresqlRepository.findById(routeOracle.getCodVeiculo());
                vehicle.ifPresent(existingRoute::setVehicle);

                if(!existingRoute.getCharge().equals(routeOracle.getNumCar())){
                    existingRoute.setCharge(routeOracle.getNumCar());
                    isUpdated = true;
                }
                if (!existingRoute.getNumMdfe().equals(routeOracle.getNumMdfe())) {
                    existingRoute.setNumMdfe(routeOracle.getNumMdfe());
                    isUpdated = true;
                }

                if (!existingRoute.getSituacaoMdfe().equals(routeOracle.getSituacaoMdfe())) {
                    existingRoute.setSituacaoMdfe(routeOracle.getSituacaoMdfe());
                    isUpdated = true;
                }

                if (!existingRoute.getTotalWeight().equals(routeOracle.getTotalPeso())) {
                    existingRoute.setTotalWeight(routeOracle.getTotalPeso());
                    isUpdated = true;
                }

                if (!existingRoute.getStartDate().equals(routeOracle.getDtSaida())) {
                    existingRoute.setStartDate(routeOracle.getDtSaida());
                    isUpdated = true;
                }
                if (!existingRoute.getEndDate().equals(routeOracle.getDtSaida())) {
                    existingRoute.setEndDate(routeOracle.getDtSaida());
                    isUpdated = true;
                }
                if(routeOracle.getSituacaoMdfe() == 102){
                    existingRoute.setStatus(StatusRouter.CONCLUIDA);
                }

                if (isUpdated) {
                    routesToSave.add(existingRoute);
                }
            } else {
                RoutePostgresql newRoute = new RoutePostgresql();
                newRoute.setCharge(routeOracle.getNumCar());
                newRoute.setNumMdfe(routeOracle.getNumMdfe());
                newRoute.setSituacaoMdfe(routeOracle.getSituacaoMdfe());
                newRoute.setTotalWeight(routeOracle.getTotalPeso());
                newRoute.setStartDate(routeOracle.getDtSaida());

                Optional<DriverPostgresql> driver = driverPostgresqlRepository.findById(routeOracle.getCodMotorista());
                driver.ifPresent(newRoute::setDriver);

                Optional<VehiclePostgresql> vehicle = vehiclePostgresqlRepository.findById(routeOracle.getCodVeiculo());
                vehicle.ifPresent(newRoute::setVehicle);

                List<Long> clientIds = routeOracle.getCodCliAsList();
                List<ClientPostgresql> clients = clientPostgresqlRepository.findAllById(clientIds);
                newRoute.setClients(clients);

                routesToSave.add(newRoute);
            }
        }

        if (!routesToSave.isEmpty()) {
            routePostgresqlRepository.saveAll(routesToSave);
            return "Routes synchronized successfully!";
        }

        return "No changes detected for routes.";
    }

}
