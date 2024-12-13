package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.VehiclePostgresql;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclePostgresqlRepository extends JpaRepository<VehiclePostgresql,Long> {

    Boolean existsByCodVehicle(Integer codVehicle);
}
