package com.arthenyo.rotapro_backend.controllers;

import com.arthenyo.rotapro_backend.customErro.GasStationUsageDTO;
import com.arthenyo.rotapro_backend.dto.DriverSupplyCountDTO;
import com.arthenyo.rotapro_backend.dto.DriverTotalCostDTO;
import com.arthenyo.rotapro_backend.dto.FuelSupplyDTO;
import com.arthenyo.rotapro_backend.services.FuelSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/abasteciemnto")
public class FuelSupplyController {

    @Autowired
    private FuelSupplyService fuelSupplyService;
    @GetMapping("/date-range")
    public Page<FuelSupplyDTO> findByDateRange(
            @RequestParam("startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable
    ) {
        return fuelSupplyService.findByDateRange(startDate, endDate, pageable);
    }
    @GetMapping("/vehicle-date-range")
    public Page<FuelSupplyDTO> findByVehicleAndDateRange(
            @RequestParam("vehicleId") Long vehicleId,
            @RequestParam("startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable
    ) {
        return fuelSupplyService.findByVehicleAndDateRange(vehicleId, startDate, endDate, pageable);
    }
    @GetMapping("/gas-station-usage")
    public List<GasStationUsageDTO> getGasStationUsage() {
        return fuelSupplyService.getGasStationUsage();
    }
    @GetMapping("/driver-count")
    public List<DriverSupplyCountDTO> countSuppliesByDriver() {
        return fuelSupplyService.getSuppliesCountByDriver();
    }

    @GetMapping("/driver-total-cost")
    public List<DriverTotalCostDTO> sumCostByDriver() {
        return fuelSupplyService.getTotalCostByDriver();
    }

    @PostMapping()
    public ResponseEntity<FuelSupplyDTO> insert(@RequestBody FuelSupplyDTO dto){
        dto = fuelSupplyService.createFuelSupply(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuelSupplyDTO> update(@PathVariable Long id, @RequestBody FuelSupplyDTO dto) {
        FuelSupplyDTO updatedDto = fuelSupplyService.updateFuelSupply(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fuelSupplyService.deleteFuelSupply(id);
        return ResponseEntity.noContent().build();
    }

}
