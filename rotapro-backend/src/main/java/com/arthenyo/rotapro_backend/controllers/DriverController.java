package com.arthenyo.rotapro_backend.controllers;

import com.arthenyo.rotapro_backend.model.model_postgresql.DriverPostgresql;
import com.arthenyo.rotapro_backend.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping
    public ResponseEntity<String> createDriver(){
        return ResponseEntity.ok(driverService.syncDrivers());
    }
}
