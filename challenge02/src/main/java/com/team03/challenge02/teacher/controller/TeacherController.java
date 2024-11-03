package com.team03.challenge02.teacher.controller;

import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.teacher.dto.LoginRequest;
import com.team03.challenge02.teacher.dto.RecoveryJwtTokenDTO;
import com.team03.challenge02.teacher.dto.TeacherDTO;
import com.team03.challenge02.teacher.entity.Teacher;
import com.team03.challenge02.teacher.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAll() {
        List<Teacher> teachers = teacherService.getAll();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable long id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Teacher> create(@RequestBody @Valid TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher(
                teacherDTO.firstName(),
                teacherDTO.lastName(),
                teacherDTO.email(),
                teacherDTO.birthDate(),
                teacherDTO.password()
        );
        Teacher createdTeacher = teacherService.create(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return ResponseEntity.noContent().build();
    }

    /*@PostMapping("/{id}/disciplines")
    public ResponseEntity<Teacher> addDiscipline(@PathVariable long id, @RequestBody Discipline discipline) {
        var teacher = teacherService.getById(id);
        *//*teacherService.*//*
    }*/

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDTO> login(@RequestBody @Valid LoginRequest loginRequest) {
        RecoveryJwtTokenDTO token = teacherService.authenticateUser(loginRequest);
        return ResponseEntity.ok(token);
    }

}
