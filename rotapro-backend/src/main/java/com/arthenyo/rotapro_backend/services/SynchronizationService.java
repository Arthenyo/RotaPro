package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.model.model_oracle.*;
import com.arthenyo.rotapro_backend.model.model_postgresql.*;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.Availabilities;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusRouter;
import com.arthenyo.rotapro_backend.repositories.repository_oracle.*;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private UserPostgresqlRepository userPostgresqlRepository;
    @Autowired
    private RolePostgresqlRepository rolePostgresqlRepository;
    @Autowired
    private HelperOracleRepository helperOracleRepository;
    @Autowired
    private HelperPostgresqlRepository helperPostgresqlRepository;


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

                if (!existingVehicle.getDescription().equals(vehicleOracle.getDescription())) {
                    existingVehicle.setDescription(vehicleOracle.getDescription());
                    isUpdated = true;
                }

                if (!existingVehicle.getMark().equals(vehicleOracle.getMark())) {
                    existingVehicle.setMark(vehicleOracle.getMark());
                    isUpdated = true;
                }

                if (!existingVehicle.getTypeVehicle().equals(vehicleOracle.getTypeWheeled())) {
                    existingVehicle.setTypeVehicle(vehicleOracle.getTypeWheeled());
                    isUpdated = true;
                }

                if (!existingVehicle.getColor().equals(vehicleOracle.getColor())) {
                    existingVehicle.setColor(vehicleOracle.getColor());
                    isUpdated = true;
                }
                if (!existingVehicle.getLoadWeightKg().equals(vehicleOracle.getLoadWeightKg())) {
                    existingVehicle.setLoadWeightKg(vehicleOracle.getLoadWeightKg());
                    isUpdated = true;
                }

                if (!existingVehicle.getQtAxes().equals(vehicleOracle.getQtAxes())) {
                    existingVehicle.setQtAxes(vehicleOracle.getQtAxes());
                    isUpdated = true;
                }

                if (!existingVehicle.getQntWheels().equals(vehicleOracle.getQntWheels())) {
                    existingVehicle.setQntWheels(vehicleOracle.getQntWheels());
                    isUpdated = true;
                }

                if (!existingVehicle.getQntLiters().equals(vehicleOracle.getQntLiters())) {
                    existingVehicle.setQntLiters(vehicleOracle.getQntLiters());
                    isUpdated = true;
                }

                if (!existingVehicle.getFuel().equals(vehicleOracle.getFuel())) {
                    existingVehicle.setFuel(vehicleOracle.getFuel());
                    isUpdated = true;
                }

                if (!existingVehicle.getOwner().equals(vehicleOracle.getOwner())) {
                    existingVehicle.setOwner(vehicleOracle.getOwner());
                    isUpdated = true;
                }
                if (isUpdated) {
                    vehiclesToSave.add(existingVehicle);
                }
            } else {

                VehiclePostgresql newVehicle = new VehiclePostgresql();
                newVehicle.setCodVehicle(vehicleOracle.getCodVehicle());
                newVehicle.setPlate(vehicleOracle.getPlate());
                newVehicle.setDescription(vehicleOracle.getDescription());
                newVehicle.setMark(vehicleOracle.getMark());
                newVehicle.setTypeVehicle(vehicleOracle.getTypeWheeled());
                newVehicle.setColor(vehicleOracle.getColor());
                newVehicle.setLoadWeightKg(vehicleOracle.getLoadWeightKg());
                newVehicle.setQtAxes(vehicleOracle.getQtAxes());
                newVehicle.setQntWheels(vehicleOracle.getQntWheels());
                newVehicle.setQntLiters(vehicleOracle.getQntLiters());
                newVehicle.setFuel(vehicleOracle.getFuel());
                newVehicle.setOwner(vehicleOracle.getOwner());
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

        RolePostgresql roleDriver = rolePostgresqlRepository
                .findByAuthority("ROLE_DRIVER")
                .orElseThrow(() -> new RuntimeException("ROLE_DRIVER not found in database!"));

        for (DriverOracle driverOracle : driverOracleList) {

            Optional<DriverPostgresql> existingDriverOptional = driverPostgresqlRepository
                    .findByRegistration(driverOracle.getRegistration());

            if (existingDriverOptional.isPresent()) {
                DriverPostgresql existingDriver = existingDriverOptional.get();
                boolean isUpdated = false;

                if (!Objects.equals(existingDriver.getName(), driverOracle.getName())) {
                    existingDriver.setName(driverOracle.getName());
                    isUpdated = true;
                }

                if (!Objects.equals(existingDriver.getCnh(), driverOracle.getCnh())) {
                    existingDriver.setCnh(driverOracle.getCnh());
                    isUpdated = true;
                }

                if (!Objects.equals(existingDriver.getCategoryCNH(), driverOracle.getCategoryCNH())) {
                    existingDriver.setCategoryCNH(driverOracle.getCategoryCNH());
                    isUpdated = true;
                }

                if (!Objects.equals(existingDriver.getValidityCNH(), driverOracle.getValidityCNH())) {
                    existingDriver.setValidityCNH(driverOracle.getValidityCNH());
                    isUpdated = true;
                }

                if (existingDriver.getUser() == null) {
                    UserPostgresql newUser = new UserPostgresql();
                    newUser.setName(driverOracle.getName());
                    newUser.setEmail(geraEmailPeloNome(driverOracle.getName()));
                    newUser.setPhone("11999999999");
                    newUser.setBirthDate(LocalDate.now()); // ou outro valor
                    newUser.setPassword("padrao123");

                    newUser.getRoles().add(roleDriver);

                    userPostgresqlRepository.save(newUser);

                    existingDriver.setUser(newUser);
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

                UserPostgresql newUser = new UserPostgresql();
                newUser.setName(driverOracle.getName());
                newUser.setEmail(geraEmailPeloNome(driverOracle.getName()));
                newUser.setPhone("11999999999");
                newUser.setBirthDate(LocalDate.now());
                newUser.setPassword("padrao123");

                newUser.getRoles().add(roleDriver);

                userPostgresqlRepository.save(newUser);

                newDriver.setUser(newUser);

                driversToSave.add(newDriver);
            }
        }
        if (!driversToSave.isEmpty()) {
            driverPostgresqlRepository.saveAll(driversToSave);
            return "Drivers synchronized successfully!";
        }

        return "No changes detected for drivers.";
    }
    public String syncHelpers(Integer codsetor, Integer codfilial) {
        List<HelperOracle> helperOracleList = helperOracleRepository.getAllHelper(codsetor, codfilial);
        if (helperOracleList.isEmpty()) {
            return "No helpers found in Oracle Database!";
        }

        RolePostgresql roleHelper = rolePostgresqlRepository
                .findByAuthority("ROLE_HELPER")
                .orElseThrow(() -> new RuntimeException("ROLE_HELPER not found in database!"));

        List<HelperPostgresql> helpersToSave = new ArrayList<>();

        for (HelperOracle helperOracle : helperOracleList) {
            Optional<HelperPostgresql> optionalHelperPostgres =
                    helperPostgresqlRepository.findByRegistration(helperOracle.getRegistration());

            if (optionalHelperPostgres.isPresent()) {
                HelperPostgresql existingHelper = optionalHelperPostgres.get();
                boolean isUpdated = false;

                if (!Objects.equals(existingHelper.getName(), helperOracle.getName())) {
                    existingHelper.setName(helperOracle.getName());
                    isUpdated = true;
                }

                if (!Objects.equals(existingHelper.getSector(), helperOracle.getSector())) {
                    existingHelper.setSector(helperOracle.getSector());
                    isUpdated = true;
                }

                if (!Objects.equals(existingHelper.getEmail(), helperOracle.getEmail())) {
                    existingHelper.setEmail(helperOracle.getEmail());
                    isUpdated = true;
                }

                if (!Objects.equals(existingHelper.getFone(), helperOracle.getFone())) {
                    existingHelper.setFone(helperOracle.getFone());
                    isUpdated = true;
                }

                if (existingHelper.getUser() == null) {
                    UserPostgresql newUser = new UserPostgresql();
                    newUser.setName(helperOracle.getName());
                    newUser.setEmail(helperOracle.getEmail());
                    newUser.setPhone("11999999999");
                    newUser.setBirthDate(LocalDate.now());
                    newUser.setPassword("padrao123");
                    newUser.getRoles().add(roleHelper);

                    userPostgresqlRepository.save(newUser);

                    existingHelper.setUser(newUser);
                    isUpdated = true;
                }

                if (isUpdated) {
                    helpersToSave.add(existingHelper);
                }

            } else {
                HelperPostgresql newHelper = new HelperPostgresql();
                newHelper.setRegistration(helperOracle.getRegistration());
                newHelper.setName(helperOracle.getName());
                newHelper.setSector(helperOracle.getSector());
                newHelper.setEmail(helperOracle.getEmail());
                newHelper.setFone(helperOracle.getFone());

                UserPostgresql newUser = new UserPostgresql();
                newUser.setName(helperOracle.getName());
                newUser.setEmail(helperOracle.getEmail());
                newUser.setPhone("11999999999");
                newUser.setBirthDate(LocalDate.now());
                newUser.setPassword("padrao123");
                newUser.getRoles().add(roleHelper);

                userPostgresqlRepository.save(newUser);

                newHelper.setUser(newUser);

                helpersToSave.add(newHelper);
            }
        }

        if (!helpersToSave.isEmpty()) {
            helperPostgresqlRepository.saveAll(helpersToSave);
            return "Helpers synchronized successfully!";
        }

        return "No changes detected for helpers.";
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

            Optional<DriverPostgresql> driver = driverPostgresqlRepository.findByRegistration(routeOracle.getCodMotorista());
            Optional<VehiclePostgresql> vehicle = vehiclePostgresqlRepository.findByCodVehicle(routeOracle.getCodVeiculo());

            if (driver.isEmpty() || vehicle.isEmpty()) {
                continue;
            }

            List<Integer> clientIds = routeOracle.getCodCliAsList();
            List<ClientPostgresql> clientEntities = clientIds.stream()
                    .map(clientPostgresqlRepository::findByCodClient)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            if (clientEntities.isEmpty()) {
                continue;
            }

            if (existingRouteOptional.isPresent()) {
                RoutePostgresql existingRoute = existingRouteOptional.get();
                boolean isUpdated = false;

                existingRoute.setDriver(driver.get());
                existingRoute.setVehicle(vehicle.get());

                if (!existingRoute.getCharge().equals(routeOracle.getNumCar())) {
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
                if (routeOracle.getSituacaoMdfe() == 102) {
                    existingRoute.setStatus(StatusRouter.CONCLUIDA);
                    isUpdated = true;
                }

                List<Long> existingClientIds = existingRoute.getClients().stream()
                        .map(ClientPostgresql::getId)
                        .collect(Collectors.toList());
                List<Long> newClientIds = clientEntities.stream()
                        .map(ClientPostgresql::getId)
                        .collect(Collectors.toList());

                if (!existingClientIds.containsAll(newClientIds) || !newClientIds.containsAll(existingClientIds)) {
                    existingRoute.setClients(clientEntities);
                    isUpdated = true;
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
                newRoute.setStatus(StatusRouter.EM_ANDAMENTO);

                newRoute.setDriver(driver.get());
                newRoute.setVehicle(vehicle.get());

                newRoute.setClients(clientEntities);

                routesToSave.add(newRoute);
            }
        }

        if (!routesToSave.isEmpty()) {
            routePostgresqlRepository.saveAll(routesToSave);
            return "Routes synchronized successfully!";
        }

        return "No changes detected for routes.";
    }
    private String geraEmailPeloNome(String name) {
        if (name == null || name.isEmpty()) {
            return "nome.invalido@empresa.com";
        }
        String nomeSemAcentos = Normalizer
                .normalize(name, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        nomeSemAcentos = nomeSemAcentos.toLowerCase();

        String[] partes = nomeSemAcentos.split("\\s+");
        String baseEmail = String.join(".", partes);

        String dominio = "empresa.com";

        String email = baseEmail + "@" + dominio;
        int contador = 1;

        while (userPostgresqlRepository.existsByEmail(email)) {
            email = baseEmail + contador + "@" + dominio;
            contador++;
        }

        return email;
    }

}
