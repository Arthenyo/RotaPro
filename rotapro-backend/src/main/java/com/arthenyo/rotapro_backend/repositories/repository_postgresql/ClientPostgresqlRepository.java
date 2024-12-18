package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.ClientPostgresql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientPostgresqlRepository extends JpaRepository<ClientPostgresql, Long> {
    Optional<ClientPostgresql> findByCodClient(Integer codcli);
}
