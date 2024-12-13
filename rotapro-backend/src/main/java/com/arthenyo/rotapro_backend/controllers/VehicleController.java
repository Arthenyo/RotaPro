package com.arthenyo.rotapro_backend.controllers;

import com.arthenyo.rotapro_backend.services.DriverService;
import com.arthenyo.rotapro_backend.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<String> createDriver(){
        return ResponseEntity.ok(vehicleService.createVehicle());
    }
}
