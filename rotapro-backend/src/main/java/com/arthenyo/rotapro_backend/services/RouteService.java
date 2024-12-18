package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.dto.RouteDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.RoutePostgresql;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.RoutePostgresqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RouteService {
    @Autowired
    private RoutePostgresqlRepository routePostgresqlRepository;

    public RouteDTO getRouteByNumCar(Integer numCar) {
        Optional<RoutePostgresql> route = routePostgresqlRepository.findByCharge(numCar);
        return new RouteDTO(route.get());
    }
}
