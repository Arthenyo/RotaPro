package com.arthenyo.rotapro_backend.controllers;

import com.arthenyo.rotapro_backend.dto.UserDTO;
import com.arthenyo.rotapro_backend.dto.UserInsertDTO;
import com.arthenyo.rotapro_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/routes")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable){
        Page<UserDTO> user = userService.findAll(pageable);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findAll(@PathVariable Long id){
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<UserDTO>> buscarUsuariosPorNome(@RequestParam String nome) {
        List<UserDTO> usuarios = userService.findByName(nome);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> insert(@RequestBody UserInsertDTO dto){
        UserDTO newDTO = userService.createUser(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(newDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(newDTO);
    }
}
