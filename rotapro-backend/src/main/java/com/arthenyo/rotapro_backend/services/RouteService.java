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


        if (dto.getHelpers() != null) {
            List<HelperPostgresql> helpers = dto.getHelpers().stream()
                    .map(helperReg -> helperPostgresqlRepository.findByName(helperReg)
                            .orElseThrow(() -> new ResponseStatusException("Ajudante não encontrado com matrícula " + helperReg)))
                    .collect(Collectors.toList());
            route.setHelpers(helpers);
        }

        route.setKminicial(dto.getKminicial());
        route.setKmfinal(dto.getKmfinal());

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
