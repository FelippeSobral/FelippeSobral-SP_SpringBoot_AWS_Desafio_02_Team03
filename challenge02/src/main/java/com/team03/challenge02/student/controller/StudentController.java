package com.team03.challenge02.student.controller;

import com.team03.challenge02.student.dto.StudentDto;
import com.team03.challenge02.student.dto.mapper.StudentMapper;
import com.team03.challenge02.student.entity.Student;
import com.team03.challenge02.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDto> createUser(@Valid @RequestBody StudentDto studentDto) {
        Student newStudent = studentService.save(StudentMapper.toStudent(studentDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(StudentMapper.toStudentDto(newStudent));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getById(id);
        return ResponseEntity.ok(student);
    }
    @GetMapping
    public ResponseEntity<List<StudentDto>> getUsers() {
        List<Student> userEntities = studentService.getAll();
        return ResponseEntity.ok(StudentMapper.toListDTO(userEntities));
    }
}
