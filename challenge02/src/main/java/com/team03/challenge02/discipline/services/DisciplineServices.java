package com.team03.challenge02.discipline.services;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.discipline.dto.DisciplineDto;
import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.discipline.repository.DisciplineRepository;
import com.team03.challenge02.exception.NameUniqueViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineServices {

    private final DisciplineRepository disciplineRepository;

    public DisciplineServices(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public List<Discipline> findAll(){
        return disciplineRepository.findAll();
    }

    public Optional<Discipline> getById(long id) {
        return disciplineRepository.findById(id);
    }
    public void delete(Long id){
        disciplineRepository.deleteById(id);
    }
}
