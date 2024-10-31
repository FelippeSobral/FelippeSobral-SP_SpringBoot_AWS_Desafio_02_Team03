package com.team03.challenge02.discipline.services;

import com.team03.challenge02.discipline.dto.DisciplineDto;
import com.team03.challenge02.discipline.repository.DisciplineRepository;
import org.springframework.stereotype.Service;

@Service
public class DisciplineServices {
    private final DisciplineRepository disciplineRepository;
    //private final Course

    public DisciplineServices(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public DisciplineDto postDiscipline(DisciplineDto disciplineDto){
        return disciplineDto;
    }
}
