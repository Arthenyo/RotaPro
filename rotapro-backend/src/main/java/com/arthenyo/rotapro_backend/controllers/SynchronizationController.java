package com.arthenyo.rotapro_backend.controllers;

import com.arthenyo.rotapro_backend.services.SynchronizationService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

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
    public ResponseEntity<String> createDriver(@RequestParam("codfilial") Integer codfilial){
        return ResponseEntity.ok(synchronizationService.syncDrivers(codfilial));
    }
    @PostMapping("/helpers")
    public ResponseEntity<String> createHelpers(@RequestParam("codsetor") Integer codsetor, @RequestParam("codfilial") Integer codfilial){
        return ResponseEntity.ok(synchronizationService.syncHelpers(codsetor, codfilial));
    }
    @PostMapping("/vehicles")
    public ResponseEntity<String> createVehicle(@RequestParam("codfilial") Integer codfilial){
        return ResponseEntity.ok(synchronizationService.syncVehicles(codfilial));
    }
    @PostMapping("/routes")
    public ResponseEntity<String> createRoute(@RequestParam("codfilial") Integer codfilial) {
        return ResponseEntity.ok(synchronizationService.syncRoutes(codfilial));
    }
    @PostMapping("/branches")
    public ResponseEntity<String> createBranche(@RequestParam("codigo") Integer codigo) {
        return ResponseEntity.ok(synchronizationService.syncBranches(codigo));
    }
}
