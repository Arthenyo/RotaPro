package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.DriverPostgresql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverPostgresqlRepository extends JpaRepository<DriverPostgresql, Long> {
    boolean existsByRegistration(Integer registration);
    DriverPostgresql findByCnh(String cnh);
    Optional<DriverPostgresql> findByRegistration(Integer registration);
    Optional<DriverPostgresql> findByName(String nome);
}
