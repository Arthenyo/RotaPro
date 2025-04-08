package com.arthenyo.rotapro_backend.controllers;

import com.arthenyo.rotapro_backend.dto.RouteDTO;
import com.arthenyo.rotapro_backend.dto.RouteMinDTO;
import com.arthenyo.rotapro_backend.dto.RouteStopRequestDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusRouter;
import com.arthenyo.rotapro_backend.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rotas")
public class RouteController {

    @Autowired
    private RouteService routeService;
    @PutMapping("/{id}")
    public ResponseEntity<RouteDTO> updateRoute(@PathVariable Long id, @RequestBody RouteDTO dto) {
        RouteDTO updatedRoute = routeService.updateRoute(id, dto);
        return ResponseEntity.ok(updatedRoute);
    }
    @PostMapping("/add-stop")
    public ResponseEntity<String> addRouteStop(@RequestBody RouteStopRequestDTO requestDTO) {
        String responseMessage = routeService.addRouteStop(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
    @PutMapping("start/{id}")
    public ResponseEntity<RouteDTO> StartRoute(@PathVariable Long id, @RequestBody RouteDTO dto) {
        RouteDTO updatedRoute = routeService.StartRoute(id, dto);
        return ResponseEntity.ok(updatedRoute);
    }
    @PutMapping("finish/{id}")
    public ResponseEntity<RouteDTO> FinishRoute(@PathVariable Long id, @RequestBody RouteDTO dto) {
        RouteDTO updatedRoute = routeService.FinishRoute(id, dto);
        return ResponseEntity.ok(updatedRoute);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RouteDTO> getRouteById(@PathVariable Long id) {
        RouteDTO route = routeService.getRouteById(id);
        return ResponseEntity.ok(route);
    }
    @GetMapping
    public ResponseEntity<Page<RouteMinDTO>> getAllRoutes(Pageable pageable) {
        Page<RouteMinDTO> routes = routeService.getAllRoutes(pageable);
        return ResponseEntity.ok(routes);
    }
    @GetMapping("/driver")
    public ResponseEntity<Page<RouteMinDTO>> getAllRoutesUser(Pageable pageable) {
        Page<RouteMinDTO> routes = routeService.getAllRoutesUser(pageable);
        return ResponseEntity.ok(routes);
    }
    @GetMapping("/driver/open")
    public ResponseEntity<List<RouteMinDTO>> getAllRoutesUserOpen() {
        List<RouteMinDTO> routes = routeService.getAllRoutesUserOpen();
        return ResponseEntity.ok(routes);
    }
    @GetMapping("/status")
    public ResponseEntity<List<RouteMinDTO>> getRoutesByStatus(@RequestParam("status") StatusRouter status) {
        List<RouteMinDTO> routes = routeService.getRoutesByStatus(status);
        return ResponseEntity.ok(routes);
    }
    @GetMapping("/periodo")
    public ResponseEntity<List<RouteMinDTO>> getRoutesByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<RouteMinDTO> routes = routeService.getRoutesByDateRange(startDate, endDate);
        return ResponseEntity.ok(routes);
    }
}
