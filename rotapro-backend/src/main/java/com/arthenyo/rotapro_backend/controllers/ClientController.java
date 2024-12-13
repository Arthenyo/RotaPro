package com.arthenyo.rotapro_backend.controllers;

import com.arthenyo.rotapro_backend.services.ClientService;
import com.arthenyo.rotapro_backend.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<String> createClient(){
        return ResponseEntity.ok(clientService.syncClients());
    }
}
