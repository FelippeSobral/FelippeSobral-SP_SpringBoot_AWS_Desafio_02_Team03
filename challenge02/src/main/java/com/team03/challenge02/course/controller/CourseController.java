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
}
