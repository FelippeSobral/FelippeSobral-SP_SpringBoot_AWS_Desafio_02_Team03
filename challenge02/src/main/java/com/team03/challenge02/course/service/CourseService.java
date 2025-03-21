package com.team03.challenge02.course.service;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.exception.EntityIdNotFoundException;
import com.team03.challenge02.exception.EntityNameNotFoundException;
import com.team03.challenge02.exception.NameUniqueViolationException;
import com.team03.challenge02.course.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Course create(Course course) {
        try {
            return repository.save(course);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw  new NameUniqueViolationException(String.format("Name {%s} exists in our data base", course.getName()));
        }
    }

    public List<Course> findAll(){
        return repository.findAll();
    }

    public Course findById(long id) {
        return repository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(String.format("Name of id {%s} not found", id)));
    }

    public Course findByNameContaining(String name) {
        try {
            return repository.findByNameContaining(name);
        } catch (org.springframework.dao.IncorrectResultSizeDataAccessException ex){
            throw new EntityNameNotFoundException(String.format("Name {%s} not found", name));
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Course update(Long id, Course course){
        Course cr = findById(id);
        cr.setName(course.getName());
        cr.setCoordinator(course.getCoordinator());
        cr.setDescription(course.getDescription());
        cr.setDisciplinesList(course.getDisciplinesList());
        return repository.save(cr);
    }
}
