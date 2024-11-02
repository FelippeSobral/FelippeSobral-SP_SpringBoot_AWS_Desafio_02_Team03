package com.team03.challenge02.course.controller;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course){
        Course cr = service.update(id, course);
        return ResponseEntity.ok().body(cr);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course){
        Course cs = service.create(course);
        return ResponseEntity.status(201).body(cs);
    }

    @GetMapping
    public ResponseEntity<List<Course>> findAll(){
        List<Course> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id){
        Course cs = service.findById(id);
        return ResponseEntity.ok().body(cs);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Course> findByName(@PathVariable String name){
        Course cs = service.findByNameContaining(name);
        return ResponseEntity.ok().body(cs);
    }
}
