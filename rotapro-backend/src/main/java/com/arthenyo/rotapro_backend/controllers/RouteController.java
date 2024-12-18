package com.arthenyo.rotapro_backend.controllers;

import com.arthenyo.rotapro_backend.dto.RouteDTO;
import com.arthenyo.rotapro_backend.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/{numCar}")
    public ResponseEntity<RouteDTO> getRouteByNumCar(@PathVariable Integer numCar){
        return ResponseEntity.ok(routeService.getRouteByNumCar(numCar));
    }


}
