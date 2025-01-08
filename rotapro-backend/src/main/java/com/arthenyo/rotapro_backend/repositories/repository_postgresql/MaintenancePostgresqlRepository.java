package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.MaintenancePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.VehiclePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusMaintenance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MaintenancePostgresqlRepository extends JpaRepository<MaintenancePostgresql,Long> {
    Page<MaintenancePostgresql> findByVehicle(VehiclePostgresql vehiclePostgresql, Pageable pageable);
    @Query("SELECT m FROM MaintenancePostgresql m WHERE m.nextMaintenanceDate >= :date")
    Page<MaintenancePostgresql> findAllWithNextMaintenanceDateAfter(@Param("date") LocalDate date, Pageable pageable);
    Page<MaintenancePostgresql> findAllByStatus(StatusMaintenance status, Pageable pageable);
    Page<MaintenancePostgresql> findAllByTotalCostGreaterThan(Double cost, Pageable pageable);
    Page<MaintenancePostgresql> findAllByMaintenanceDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
