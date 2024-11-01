package com.team03.challenge02.teacher.service;

import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.teacher.entity.Teacher;
import com.team03.challenge02.teacher.exceptions.DuplicatedDisciplinesException;
import com.team03.challenge02.teacher.exceptions.InvalidCouseSubjectException;
import com.team03.challenge02.teacher.exceptions.TooManySubjectsException;
import com.team03.challenge02.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Transactional
    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Transactional(readOnly = true)
    public Optional<Teacher> getById(long id) {
        return teacherRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Transactional
    public void delete(long id) {
        teacherRepository.deleteById(id);
    }

    private void validateDiscipline(Teacher teacher) {
        List<Discipline> holdingSubjects = teacher.getHoldingSubjects();
        List<Discipline> substituteSubjects = teacher.getSubstituteSubjects();

        if (holdingSubjects.size() > 2) {
            throw new TooManySubjectsException("Too many holding subjects");
        }

        if(substituteSubjects.size() > 2) {
            throw new TooManySubjectsException("Too many substitute subjects");
        }

        Set<Discipline> allDisciplines = new HashSet<>();
        allDisciplines.addAll(holdingSubjects);
        allDisciplines.addAll(substituteSubjects);

        if(allDisciplines.size() < (holdingSubjects.size() + substituteSubjects.size())) {
            throw new DuplicatedDisciplinesException("Duplicate disciplines is not allowed");
        }

        for(Discipline discipline : holdingSubjects) {
            if(!discipline.getFullTeacher().getCourse().equals(teacher.getCourse())) {
                throw new InvalidCouseSubjectException("Holding subjects most be from teacher course");
            }
        }

        for(Discipline discipline : substituteSubjects) {
            if(substituteSubjects.indexOf(discipline) < 2 && !discipline.getFullTeacher().getCourse().equals(teacher.getCourse())) {
                throw new InvalidCouseSubjectException("Substitute subjects most be from teacher course");
            }
        }
    }

}
