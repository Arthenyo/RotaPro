package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.MaintenancePostgresql;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenancePostgresqlRepository extends JpaRepository<MaintenancePostgresql,Long> {
}
