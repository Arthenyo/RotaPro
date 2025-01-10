package com.arthenyo.rotapro_backend.controllers;

import com.arthenyo.rotapro_backend.model.model_postgresql.EstablishmentsPostgresql;
import com.arthenyo.rotapro_backend.services.EstablishmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/establishments")
public class EstablishmentsController {
    @Autowired
    private EstablishmentsService establishmentsService;

    @PostMapping()
    public ResponseEntity<String> createEstablishments(@RequestBody EstablishmentsPostgresql establishments){
        return ResponseEntity.ok().body(establishmentsService.createEstablishments(establishments));
    }
}
