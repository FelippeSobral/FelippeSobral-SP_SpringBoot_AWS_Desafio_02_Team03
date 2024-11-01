package com.team03.challenge02.coordinator.service;

import com.team03.challenge02.coordinator.entity.Coordinator;
import com.team03.challenge02.coordinator.repository.CoordinatorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatorService {

    private final CoordinatorRepository repository;

    public CoordinatorService(CoordinatorRepository repository) {
        this.repository = repository;
    }

    public Coordinator create(Coordinator cr){
        return repository.save(cr);
    }

    public List<Coordinator> findAll(){
        return repository.findAll();
    }
}
