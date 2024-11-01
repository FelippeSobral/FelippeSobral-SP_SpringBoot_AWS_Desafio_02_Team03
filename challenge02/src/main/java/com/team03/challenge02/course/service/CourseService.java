package com.team03.challenge02.course.service;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.course.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Course create(Course course) {
        return repository.save(course);
    }

    public List<Course> findAll(){
        return repository.findAll();
    }

    public Optional<Course> findById(long id) {
        return repository.findById(id);
    }
}
