package com.team03.challenge02.teacher.controller;

import com.team03.challenge02.teacher.dto.TeacherCreateDTO;
import com.team03.challenge02.teacher.entity.Teacher;
import com.team03.challenge02.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/teacher")
public class teacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<Teacher> getAll() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Teacher> getById(@PathVariable long id) {
        return teacherService.getById(id);
    }

    @PostMapping
    public Teacher create(@RequestBody Teacher teacher) {
        return teacherService.create(teacher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        teacherService.delete(id);
    }

}
