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
            throw  new NameUniqueViolationException(String.format("Information exists in our data base"));
        }
    }

    public Coordinator update(Long id, Coordinator coordinator){
        Coordinator cr = findById(id);
        try {
            cr.setFirstName(coordinator.getFirstName());
            cr.setLastName(coordinator.getLastName());
            cr.setCourse(coordinator.getCourse());
            cr.setEmail(coordinator.getEmail());
            cr.setDisciplinas(coordinator.getDisciplinas());
            cr.setBirthDate(coordinator.getBirthDate());
        } catch (org.springframework.transaction.TransactionSystemException ex) {
            throw  new NameUniqueViolationException(String.format("Preencha os campos"));
        }
        return repository.save(cr);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Coordinator findById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(String.format("Information of id {%s} not found", id)));
    }

    public List<Coordinator> findAll(){
        return repository.findAll();
    }


}
