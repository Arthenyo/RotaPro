package com.arthenyo.rotapro_backend.controllers;

import com.arthenyo.rotapro_backend.dto.MaintenanceDTO;
import com.arthenyo.rotapro_backend.dto.MaintenanceMinDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.MaintenancePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.VehiclePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusMaintenance;
import com.arthenyo.rotapro_backend.services.MaintenanceService;
import com.arthenyo.rotapro_backend.services.exception.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/maintenances")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;
    @GetMapping()
    public ResponseEntity<Page<MaintenanceMinDTO>> findAll(Pageable pageable){
        Page<MaintenanceMinDTO> dto = maintenanceService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<MaintenanceMinDTO> findById(@PathVariable Long id){
        MaintenanceMinDTO dto = maintenanceService.findById(id);
        return ResponseEntity.ok(dto);
    }
    @GetMapping(value = "/vehicle/{id}")
    public ResponseEntity<Page<MaintenanceMinDTO>> findByVehicle(@PathVariable Integer id, Pageable pageable){
        Page<MaintenanceMinDTO> dto = maintenanceService.findByVehicleIdMaintenances(id, pageable);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/next")
    public Page<MaintenanceMinDTO> findNextMaintenanceDates(Pageable pageable) {
        return maintenanceService.findByNextMaintenanceDate(pageable);
    }
    @GetMapping("/status")
    public Page<MaintenanceMinDTO> findByStatus(@RequestParam("status") StatusMaintenance status, Pageable pageable) {
        if (status == null) {
            throw new ResponseStatusException("Status não pode ser nulo. Valores possíveis: PENDENTE, EM_ANDAMENTO, CONCLUIDO etc.");
        }
        return maintenanceService.findByStatusMaintenances(status, pageable);
    }
    @GetMapping("/cost-above")
    public Page<MaintenanceMinDTO> findByCostAboveMaintenances(@RequestParam("cost") Double cost, Pageable pageable) {
        if (cost == null || cost < 0) {
            throw new ResponseStatusException("O valor de 'cost' não pode ser nulo ou negativo.");
        }

        return maintenanceService.findByCostAboveMaintenances(cost, pageable);
    }
    @GetMapping("/date-range")
    public Page<MaintenanceMinDTO> findByDateRangeMaintenances(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable
    ) {
        if (startDate.isAfter(endDate)) {
            throw new ResponseStatusException("A data inicial (startDate) não pode ser maior que a data final (endDate).");
        }
        return maintenanceService.findByDateRangeMaintenances(startDate, endDate, pageable);
    }
    @PostMapping()
    public ResponseEntity<MaintenanceDTO> insert(@RequestBody MaintenanceDTO dto){
        dto = maintenanceService.createMaintenance(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
