package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.RouteStopPostgresql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteStopPostgresqlRepository extends JpaRepository<RouteStopPostgresql, Long> {
    List<RouteStopPostgresql> findByRouteId(Long routeId);
}
