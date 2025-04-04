package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.dto.*;
import com.arthenyo.rotapro_backend.model.model_postgresql.*;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusRouter;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StopType;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.*;
import com.arthenyo.rotapro_backend.services.exception.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    @Autowired
    private FuelSupplyPostgresqlRepository fuelSupplyRepository;

    @Transactional
    public RouteDTO updateRoute(Long id, RouteDTO dto) {
        RoutePostgresql route = routePostgresqlRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException("Rota ID " + id + " não encontrada."));


        route = routePostgresqlRepository.save(route);

        return new RouteDTO(route);
    }
    @Transactional
    public String addRouteStop(RouteStopRequestDTO requestDTO) {
        RouteStopPostgresql routeStopPostgresql = new RouteStopPostgresql();
        routeStopPostgresql.setRoute(routePostgresqlRepository.findById(requestDTO.getRouteStop().getRouteId())
               .orElseThrow(() -> new ResponseStatusException("Rota ID " + requestDTO.getRouteStop().getRouteId() + " não encontrada.")));

        if(routeStopPostgresql.getRoute().getKminicial() == null){
            throw new ResponseStatusException("A rota precisa estar Iniciada");
        }
        if(routeStopPostgresql.getRoute().getStatus() == StatusRouter.CONCLUIDA){
            throw new ResponseStatusException("A rota já está Concluída. voce nao pode adcionar parada em uma rota concluida");
        }

        EstablishmentsPostgresql establishment = establishmentsPostgresqlRepository.findById(requestDTO.getRouteStop().getEstablishmentId())
                .orElseThrow(() -> new ResponseStatusException("estabelecimento ID " + requestDTO.getRouteStop().getEstablishmentId() + " não encontrada."));

        routeStopPostgresql.setEstablishment(establishment);

        routeStopPostgresql.setStartTime(requestDTO.getRouteStop().getStartTime());
        routeStopPostgresql.setEndTime(requestDTO.getRouteStop().getEndTime());
        routeStopPostgresql.setCost(requestDTO.getRouteStop().getCost());

        StopType stopType = requestDTO.getRouteStop().getStopType();
        routeStopPostgresql.setStopType(stopType);

        routeStopPostgresqlRepository.save(routeStopPostgresql);

        if (stopType == StopType.ABASTECIMENTO) {
            FuelSupplyDetailsDTO fuelSupplyDetails = requestDTO.getFuelSupplyDetails();
            FuelSupplyPostgresql fuelSupply = createFuelSupply(routeStopPostgresql, fuelSupplyDetails);

            if (fuelSupply != null) {
                fuelSupplyRepository.save(fuelSupply);
            }
        }
        else if (stopType == StopType.ALIMENTACAO || stopType == StopType.PERNOITE) {
            // pode adicionar mais lógica para esses tipos de parada, como validar custo ou outro dado específico
        }
        routeStopPostgresqlRepository.save(routeStopPostgresql);

        return "Parada criada com sucesso!";
    }

    @Transactional
    public RouteDTO StartRoute(Long id, RouteDTO dto) {
        RoutePostgresql route = routePostgresqlRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException("Rota ID " + id + " não encontrada."));

        if (dto.getHelpers() != null) {
            List<HelperPostgresql> helpers = dto.getHelpers().stream()
                    .map(helperReg -> helperPostgresqlRepository.findByName(helperReg)
                            .orElseThrow(() -> new ResponseStatusException("Ajudante não encontrado com matrícula " + helperReg)))
                    .collect(Collectors.toList());
            route.setHelpers(helpers);
        }
        route.setKminicial(dto.getKminicial());
        route.setStatus(StatusRouter.EM_ANDAMENTO);

        route = routePostgresqlRepository.save(route);

        return new RouteDTO(route);
    }
    @Transactional
    public RouteDTO FinishRoute(Long id, RouteDTO dto) {
        RoutePostgresql route = routePostgresqlRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException("Rota ID " + id + " não encontrada."));

        route.setKmfinal(dto.getKmfinal());
        route.setTotalKm(dto.getKmfinal() - route.getKminicial());
        route.setEndDate(LocalDate.now());
        route.setStatus(StatusRouter.CONCLUIDA);

        route = routePostgresqlRepository.save(route);

        return new RouteDTO(route);
    }
    public RouteDTO getRouteById(Long id) {
        RoutePostgresql route = routePostgresqlRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException("Rota ID " + id + " não encontrada."));
        return new RouteDTO(route);
    }
    public Page<RouteMinDTO> getAllRoutes(Pageable pageable) {
        Page<RoutePostgresql> routes = routePostgresqlRepository.findAll(pageable);
        return routes.map(RouteMinDTO::new);
    }
    public List<RouteMinDTO> getRoutesByStatus(StatusRouter status) {
        List<RoutePostgresql> routes = routePostgresqlRepository.findByStatus(status);
        return routes.stream()
                .map(RouteMinDTO::new)
                .collect(Collectors.toList());
    }
    public List<RouteMinDTO> getRoutesByDateRange(LocalDate startDate, LocalDate endDate) {
        List<RoutePostgresql> routes = routePostgresqlRepository.findByDateRange(startDate, endDate);
        return routes.stream()
                .map(RouteMinDTO::new)
                .collect(Collectors.toList());
    }
    private FuelSupplyPostgresql createFuelSupply(RouteStopPostgresql routeStopPostgresql, FuelSupplyDetailsDTO details) {
        FuelSupplyPostgresql fuelSupply = new FuelSupplyPostgresql();

        if (routeStopPostgresql.getStopType() == StopType.ABASTECIMENTO) {

            RoutePostgresql route = routeStopPostgresql.getRoute();

            if (route != null) {

                fuelSupply.setRoute(route);
                fuelSupply.setDriver(route.getDriver());
                fuelSupply.setVehicle(route.getVehicle());

                if (routeStopPostgresql.getEndTime() != null) {
                    LocalDateTime fuelDate = routeStopPostgresql.getEndTime();
                    fuelSupply.setFuelDate(fuelDate);
                }

                fuelSupply.setEstablishments(routeStopPostgresql.getEstablishment());

                fuelSupply.setOdômetro(details.getOdometro());
                fuelSupply.setLiters(details.getLiters());
                fuelSupply.setTotalCost(details.getTotalCost());
                fuelSupply.setPricePerLiter(details.getPricePerLiter());
                fuelSupply.setObservations(details.getObservations());

                return fuelSupply;
            } else {
                throw new IllegalArgumentException("Route information is missing in routeStopPostgresql.");
            }
        }
        return null;
    }

}
