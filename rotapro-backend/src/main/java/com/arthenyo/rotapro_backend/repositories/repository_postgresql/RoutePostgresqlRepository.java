package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.RoutePostgresql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoutePostgresqlRepository extends JpaRepository<RoutePostgresql, Long> {
    Optional<RoutePostgresql> findByCharge(Integer charge);
}
