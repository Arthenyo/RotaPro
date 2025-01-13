package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.HelperPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.UserPostgresql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HelperPostgresqlRepository extends JpaRepository<HelperPostgresql, Long> {
    Optional<HelperPostgresql> findByRegistration(Integer registration);
    Optional<HelperPostgresql> findByName(String name);
    Optional<HelperPostgresql> findByUser(UserPostgresql user);
}
