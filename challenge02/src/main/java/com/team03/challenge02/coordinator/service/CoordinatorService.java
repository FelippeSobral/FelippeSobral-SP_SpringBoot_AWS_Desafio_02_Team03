package com.team03.challenge02.coordinator.service;

import com.team03.challenge02.coordinator.entity.Coordinator;
import com.team03.challenge02.coordinator.repository.CoordinatorRepository;
import com.team03.challenge02.exception.EntityIdNotFoundException;
import com.team03.challenge02.exception.NameUniqueViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatorService {

    private final CoordinatorRepository repository;

    public CoordinatorService(CoordinatorRepository repository) {
        this.repository = repository;
    }

    public Coordinator create(Coordinator cr){
        try {
            return repository.save(cr);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw  new NameUniqueViolationException(String.format("Information exists our data base"));
        }
    }

    public Coordinator findById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(String.format("Information of id {%s} not found", id)));
    }

    public List<Coordinator> findAll(){
        return repository.findAll();
    }


}
