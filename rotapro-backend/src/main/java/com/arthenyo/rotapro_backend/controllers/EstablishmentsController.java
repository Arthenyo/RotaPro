package com.arthenyo.rotapro_backend.controllers;

import com.arthenyo.rotapro_backend.dto.EstablishmentsDTO;
import com.arthenyo.rotapro_backend.dto.UserDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.EstablishmentsPostgresql;
import com.arthenyo.rotapro_backend.services.EstablishmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/establishments")
public class EstablishmentsController {
    @Autowired
    private EstablishmentsService establishmentsService;
    @GetMapping
    public ResponseEntity<Page<EstablishmentsDTO>> findAll(Pageable pageable){
        Page<EstablishmentsDTO> user = establishmentsService.findAll(pageable);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentsDTO> findAll(@PathVariable Long id){
        EstablishmentsDTO user = establishmentsService.findById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<EstablishmentsDTO>> buscarEstabelecimentoPorNome(@RequestParam String nome) {
        List<EstablishmentsDTO> usuarios = establishmentsService.findByName(nome);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping()
    public ResponseEntity<String> createEstablishments(@RequestBody EstablishmentsPostgresql establishments){
        return ResponseEntity.ok().body(establishmentsService.createEstablishments(establishments));
    }
}
