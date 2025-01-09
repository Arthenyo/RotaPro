package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.RolePostgresql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolePostgresqlRepository extends JpaRepository<RolePostgresql, Long> {
    Optional<RolePostgresql> findByAuthority(String authority);
}
