package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.RoutePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusRouter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoutePostgresqlRepository extends JpaRepository<RoutePostgresql, Long> {
    Optional<RoutePostgresql> findByCharge(Integer charge);

    List<RoutePostgresql> findByStatus(StatusRouter status);

    @Query("SELECT r FROM RoutePostgresql r WHERE r.startDate BETWEEN :startDate AND :endDate")
    List<RoutePostgresql> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT r FROM RoutePostgresql r WHERE r.driver.id = :id")
    Page<RoutePostgresql> findByRouteUser(@Param("id") Long id, Pageable pageable);

    @Query("SELECT r FROM RoutePostgresql r WHERE r.driver.id = :id AND r.status = EM_ANDAMENTO")
    List<RoutePostgresql> findByRouteUserOpen(@Param("id") Long id);
}
