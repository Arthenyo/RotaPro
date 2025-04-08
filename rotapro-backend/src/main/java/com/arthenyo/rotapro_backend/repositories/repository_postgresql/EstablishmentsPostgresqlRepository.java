package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.EstablishmentsPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.UserPostgresql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstablishmentsPostgresqlRepository extends JpaRepository<EstablishmentsPostgresql,Long> {
    Optional<EstablishmentsPostgresql> findByName(String gasStation);
    List<EstablishmentsPostgresql> findByNameStartingWith(String name);
}
