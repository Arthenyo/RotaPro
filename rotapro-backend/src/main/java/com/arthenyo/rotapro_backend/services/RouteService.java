package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.dto.RouteDTO;
import com.arthenyo.rotapro_backend.dto.RouteStopDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.*;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusRouter;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.*;
import com.arthenyo.rotapro_backend.services.exception.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RouteService {

    @Autowired
    private RoutePostgresqlRepository routePostgresqlRepository;

    @Autowired
    private DriverPostgresqlRepository driverPostgresqlRepository;

    @Autowired
    private VehiclePostgresqlRepository vehiclePostgresqlRepository;

    @Autowired
    private ClientPostgresqlRepository clientPostgresqlRepository;

    @Autowired
    private UserPostgresqlRepository userPostgresqlRepository;

    @Autowired
    private RouteStopPostgresqlRepository routeStopPostgresqlRepository;

    @Autowired
    private EstablishmentsPostgresqlRepository establishmentsPostgresqlRepository;
    @Autowired
    private HelperPostgresqlRepository helperPostgresqlRepository;

    @Transactional
    public RouteDTO updateRoute(Long id, RouteDTO dto) {
        RoutePostgresql route = routePostgresqlRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException("Rota ID " + id + " não encontrada."));

        if (dto.getDriver() != null) {
            DriverPostgresql driver = driverPostgresqlRepository.findByRegistration(dto.getDriver())
                    .orElseThrow(() -> new ResponseStatusException("Motorista não encontrado com matrícula " + dto.getDriver()));
            route.setDriver(driver);
        }

        if (dto.getVehicle() != null) {
            VehiclePostgresql vehicle = vehiclePostgresqlRepository.findByCodVehicle(dto.getVehicle())
                    .orElseThrow(() -> new ResponseStatusException("Veículo não encontrado com código " + dto.getVehicle()));
            route.setVehicle(vehicle);
        }

        if (dto.getClients() != null && !dto.getClients().isEmpty()) {
            List<ClientPostgresql> clients = dto.getClients().stream()
                    .map(clientId -> clientPostgresqlRepository.findByCodClient(clientId)
                            .orElseThrow(() -> new ResponseStatusException("Cliente não encontrado com código " + clientId)))
                    .collect(Collectors.toList());
            route.setClients(clients);
        }

        if (dto.getHelpers() != null) {
            List<HelperPostgresql> helpers = dto.getHelpers().stream()
                    .map(helperReg -> helperPostgresqlRepository.findByRegistration(helperReg)
                            .orElseThrow(() -> new ResponseStatusException("Ajudante não encontrado com matrícula " + helperReg)))
                    .collect(Collectors.toList());
            route.setHelpers(helpers);
        }

        route.setCharge(dto.getCharge());
        route.setNumMdfe(dto.getNumMdfe());
        route.setSituacaoMdfe(dto.getSituacaoMdfe());
        route.setTotalWeight(dto.getTotalWeight());
        route.setStartDate(dto.getStartDate());
        route.setEndDate(dto.getEndDate());
        route.setKminicial(dto.getKminicial());
        route.setKmfinal(dto.getKmfinal());
        route.setTotalKm(dto.getTotalKm());
        route.setStatus(dto.getStatus());

        if (dto.getStops() != null) {
            Map<Long, RouteStopPostgresql> existingStopsMap = route.getStops().stream()
                    .collect(Collectors.toMap(RouteStopPostgresql::getId, stop -> stop));

            List<RouteStopPostgresql> updatedStops = new ArrayList<>();

            for (RouteStopDTO stopDTO : dto.getStops()) {
                if (stopDTO.getId() != null) {
                    RouteStopPostgresql existingStop = existingStopsMap.get(stopDTO.getId());
                    if (existingStop != null) {
                        EstablishmentsPostgresql establishment = establishmentsPostgresqlRepository.findById(stopDTO.getEstablishmentId())
                                .orElseThrow(() -> new ResponseStatusException("Estabelecimento não encontrado com ID " + stopDTO.getEstablishmentId()));
                        existingStop.setEstablishment(establishment);
                        existingStop.setStopType(stopDTO.getStopType());
                        existingStop.setStartTime(stopDTO.getStartTime());
                        existingStop.setEndTime(stopDTO.getEndTime());
                        existingStop.setCost(stopDTO.getCost());

                        updatedStops.add(existingStop);
                        existingStopsMap.remove(stopDTO.getId());
                    } else {
                        throw new ResponseStatusException("Parada com ID " + stopDTO.getId() + " não encontrada na rota.");
                    }
                } else {
                    RouteStopPostgresql newStop = new RouteStopPostgresql();
                    newStop.setRoute(route);
                    EstablishmentsPostgresql establishment = establishmentsPostgresqlRepository.findById(stopDTO.getEstablishmentId())
                            .orElseThrow(() -> new ResponseStatusException("Estabelecimento não encontrado com ID " + stopDTO.getEstablishmentId()));
                    newStop.setEstablishment(establishment);
                    newStop.setStopType(stopDTO.getStopType());
                    newStop.setStartTime(stopDTO.getStartTime());
                    newStop.setEndTime(stopDTO.getEndTime());
                    newStop.setCost(stopDTO.getCost());

                    updatedStops.add(newStop);
                }
            }

            for (RouteStopPostgresql stopToRemove : existingStopsMap.values()) {
                routeStopPostgresqlRepository.delete(stopToRemove);
            }

            route.getStops().clear();
            route.getStops().addAll(updatedStops);
        }

        route = routePostgresqlRepository.save(route);

        return new RouteDTO(route);
    }
    public RouteDTO getRouteById(Long id) {
        RoutePostgresql route = routePostgresqlRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException("Rota ID " + id + " não encontrada."));
        return new RouteDTO(route);
    }
    public Page<RouteDTO> getAllRoutes(Pageable pageable) {
        Page<RoutePostgresql> routes = routePostgresqlRepository.findAll(pageable);
        return routes.map(RouteDTO::new);
    }
    public List<RouteDTO> getRoutesByStatus(StatusRouter status) {
        List<RoutePostgresql> routes = routePostgresqlRepository.findByStatus(status);
        return routes.stream()
                .map(RouteDTO::new)
                .collect(Collectors.toList());
    }
    public List<RouteDTO> getRoutesByDateRange(LocalDate startDate, LocalDate endDate) {
        List<RoutePostgresql> routes = routePostgresqlRepository.findByDateRange(startDate, endDate);
        return routes.stream()
                .map(RouteDTO::new)
                .collect(Collectors.toList());
    }
}
