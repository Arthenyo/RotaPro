package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.FuelSupplyPostgresql;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FuelSupplyPostgresqlRepository extends JpaRepository<FuelSupplyPostgresql,Long> {
    @Query("SELECT f FROM FuelSupplyPostgresql f " +
            "WHERE f.fuelDate BETWEEN :startDate AND :endDate")
    Page<FuelSupplyPostgresql> findAllBetweenDates(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );
    @Query("SELECT f FROM FuelSupplyPostgresql f " +
            "WHERE f.vehicle.id = :vehicleId " +
            "  AND f.fuelDate BETWEEN :startDate AND :endDate")
    Page<FuelSupplyPostgresql> findAllByVehicleAndDateRange(
            @Param("vehicleId") Long vehicleId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );
    @Query("SELECT f.establishments.name, COUNT(f.id) " +
            "FROM FuelSupplyPostgresql f " +
            "GROUP BY f.establishments.name " +
            "ORDER BY COUNT(f.id) DESC")
    List<Object[]> findGasStationUsageCount();
    @Query("SELECT f.driver.name, COUNT(f.id) " +
            "FROM FuelSupplyPostgresql f " +
            "GROUP BY f.driver.name")
    List<Object[]> countSuppliesByDriver();
    @Query("SELECT f.driver.name, SUM(f.totalCost) " +
            "FROM FuelSupplyPostgresql f " +
            "GROUP BY f.driver.name")
    List<Object[]> sumCostByDriver();



}
