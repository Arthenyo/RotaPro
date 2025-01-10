package com.arthenyo.rotapro_backend.controllers;

import com.arthenyo.rotapro_backend.services.SynchronizationService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/synchronize")
public class SynchronizationController {
    @Autowired
    private SynchronizationService synchronizationService;

    @PostMapping("/clients")
    public ResponseEntity<String> createClient(){
        return ResponseEntity.ok(synchronizationService.syncClients());
    }
    @PostMapping("/drivers")
    public ResponseEntity<String> createDriver(){
        return ResponseEntity.ok(synchronizationService.syncDrivers());
    }
    @PostMapping("/helpers")
    public ResponseEntity<String> createHelpers(@PathParam("codsetor") Integer codsetor, @PathParam("codfilial") Integer codfilial){
        return ResponseEntity.ok(synchronizationService.syncHelpers(codsetor, codfilial));
    }
    @PostMapping("/vehicles")
    public ResponseEntity<String> createVehicle(){
        return ResponseEntity.ok(synchronizationService.syncVehicles());
    }
    @PostMapping("/routes")
    public ResponseEntity<String> createRoute() {
        return ResponseEntity.ok(synchronizationService.syncRoutes());
    }
    @PostMapping("/branches")
    public ResponseEntity<String> createBranche() {
        return ResponseEntity.ok(synchronizationService.syncBranches());
    }
}
