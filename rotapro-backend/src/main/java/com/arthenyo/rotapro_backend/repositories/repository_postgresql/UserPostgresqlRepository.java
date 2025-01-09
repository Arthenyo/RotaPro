package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.UserPostgresql;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostgresqlRepository extends JpaRepository<UserPostgresql, Long> {
    boolean existsByEmail(String email);
}
