package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.DriverPostgresql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverPostgresqlRepository extends JpaRepository<DriverPostgresql, Long> {
    boolean existsByRegistration(Integer registration);


}
