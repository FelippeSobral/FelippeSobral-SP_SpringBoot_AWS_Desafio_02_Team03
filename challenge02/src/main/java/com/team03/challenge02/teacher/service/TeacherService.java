package com.team03.challenge02.teacher.service;

import com.team03.challenge02.teacher.entity.Teacher;
import com.team03.challenge02.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Optional<Teacher> getById(long id) {
        return teacherRepository.findById(id);
    }

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public void delete(long id) {
        teacherRepository.deleteById(id);
    }
}
