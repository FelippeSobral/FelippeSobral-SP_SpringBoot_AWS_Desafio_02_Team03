package com.team03.challenge02.coordinator.controller;

import com.team03.challenge02.coordinator.dto.CoordinatorDTO;
import com.team03.challenge02.coordinator.dto.mapper.CoordinatorMapper;
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

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Coordinator> create(@RequestBody Coordinator coordinator){
        Coordinator cr = service.create(coordinator);
        return ResponseEntity.status(201).body(cr);
    }

    @GetMapping
    public ResponseEntity<List<CoordinatorDTO>> findAll(){
        List<Coordinator> list = service.findAll();
        List<CoordinatorDTO> list1 = CoordinatorMapper.toAllDto(list);
        return ResponseEntity.ok().body(list1);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CoordinatorDTO> findById(@PathVariable Long id){
        Coordinator coordinator = service.findById(id);
        CoordinatorDTO dto = CoordinatorMapper.toDto(coordinator);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coordinator> update(@PathVariable Long id, @RequestBody Coordinator coordinator){
        Coordinator cr = service.update(id, coordinator);
        return ResponseEntity.ok().body(cr);
    }
}
