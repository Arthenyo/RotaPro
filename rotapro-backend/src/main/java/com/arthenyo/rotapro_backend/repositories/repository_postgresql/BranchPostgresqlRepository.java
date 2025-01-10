package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.BranchPostgresql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchPostgresqlRepository extends JpaRepository<BranchPostgresql, Long> {

    Optional<BranchPostgresql> findByCode(Integer code);
}
