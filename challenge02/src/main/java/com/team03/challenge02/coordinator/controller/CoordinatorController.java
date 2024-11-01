package com.team03.challenge02.coordinator.controller;

import com.team03.challenge02.coordinator.entity.Coordinator;
import com.team03.challenge02.coordinator.service.CoordinatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordinator")
public class CoordinatorController {

    private final CoordinatorService service;

    public CoordinatorController(CoordinatorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Coordinator> create(@RequestBody Coordinator coordinator){
        Coordinator cr = service.create(coordinator);
        return ResponseEntity.status(201).body(cr);
    }

    @GetMapping
    public ResponseEntity<List<Coordinator>> findAll(){
        List<Coordinator> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
