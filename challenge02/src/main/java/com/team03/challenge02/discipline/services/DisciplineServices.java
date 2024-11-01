package com.team03.challenge02.discipline.services;

import com.team03.challenge02.discipline.dto.DisciplineDto;
import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.discipline.repository.DisciplineRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DisciplineServices {

    private final DisciplineRepository disciplineRepository;
    //private final Course

    public Discipline create(Discipline discipline) {
        return disciplineRepository.save(discipline);
    }

    public DisciplineServices(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public DisciplineDto postDiscipline(DisciplineDto disciplineDto){
        return disciplineDto;
    }

    public Optional<Discipline> getById(long id) {
        return disciplineRepository.findById(id);
    }
}
