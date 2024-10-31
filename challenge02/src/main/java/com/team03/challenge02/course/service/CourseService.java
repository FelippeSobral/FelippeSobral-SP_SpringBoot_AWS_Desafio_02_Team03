package com.team03.challenge02.course.service;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public Course save(Course course) {
        return repository.save(course);
    }
}
