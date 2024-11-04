package com.team03.challenge02.discipline.dto.mapper;

import com.team03.challenge02.discipline.dto.DisciplineDto;
import com.team03.challenge02.discipline.entity.Discipline;

import org.springframework.stereotype.Component;

@Component
public class DisciplineMapper {

    public static DisciplineDto toDto(Discipline discipline) {
        return new DisciplineDto(
                discipline.getId(),
                discipline.getName(),
                discipline.getDescription(),
                discipline.getFullTeacher(),
                discipline.getSubstituteTeacher(),
                discipline.getStudents(),
                discipline.getCourse()
        );
    }

    public Discipline toEntity(DisciplineDto disciplineDto) {
        return new Discipline(
                disciplineDto.id(),
                disciplineDto.name(),
                disciplineDto.description(),
                disciplineDto.fullTeacherId(),
                disciplineDto.substituteTeacherId(),
                disciplineDto.studentIds(),
                disciplineDto.courseId()
        );
    }
}

