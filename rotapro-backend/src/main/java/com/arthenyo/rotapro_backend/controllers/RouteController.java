package com.arthenyo.rotapro_backend.controllers;

import com.arthenyo.rotapro_backend.dto.RouteDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusRouter;
import com.arthenyo.rotapro_backend.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rotas")
public class RouteController {

    @Autowired
    private RouteService routeService;

    // PUT: Atualizar uma Rota com Stops
    @PutMapping("/{id}")
    public ResponseEntity<RouteDTO> updateRoute(@PathVariable Long id, @RequestBody RouteDTO dto) {
        RouteDTO updatedRoute = routeService.updateRoute(id, dto);
        return ResponseEntity.ok(updatedRoute);
    }

    // GET: Buscar Rota por ID
    @GetMapping("/{id}")
    public ResponseEntity<RouteDTO> getRouteById(@PathVariable Long id) {
        RouteDTO route = routeService.getRouteById(id);
        return ResponseEntity.ok(route);
    }

    // GET: Buscar Todas as Rotas com Paginação
    @GetMapping
    public ResponseEntity<Page<RouteDTO>> getAllRoutes(Pageable pageable) {
        Page<RouteDTO> routes = routeService.getAllRoutes(pageable);
        return ResponseEntity.ok(routes);
    }

    // GET: Buscar Rotas por Status
    @GetMapping("/status")
    public ResponseEntity<List<RouteDTO>> getRoutesByStatus(@RequestParam("status") StatusRouter status) {
        List<RouteDTO> routes = routeService.getRoutesByStatus(status);
        return ResponseEntity.ok(routes);
    }

    // GET: Buscar Rotas por Período de Datas
    @GetMapping("/periodo")
    public ResponseEntity<List<RouteDTO>> getRoutesByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<RouteDTO> routes = routeService.getRoutesByDateRange(startDate, endDate);
        return ResponseEntity.ok(routes);
    }
}
