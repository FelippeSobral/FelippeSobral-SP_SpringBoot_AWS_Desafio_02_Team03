//package com.team03.challenge02.student.controller;
//
//import com.team03.challenge02.student.dto.StudentDto;
//import com.team03.challenge02.student.dto.mapper.StudentMapper;
//import com.team03.challenge02.student.entity.Student;
//import com.team03.challenge02.student.service.StudentService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("api/student")
//public class StudentController {
//
//    private final StudentService studentService;
//
//    @PostMapping
//    public ResponseEntity<StudentDto> save(@Valid @RequestBody StudentDto studentDto) {
//        Student newStudent = studentService.save(studentDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(StudentMapper.toStudentDto(newStudent));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
//        Student student = studentService.getById(id);
//        StudentDto studentResponseDto = StudentMapper.toStudentDto(student);
//        return ResponseEntity.ok(studentResponseDto);
//    }
//    @GetMapping
//    public ResponseEntity<List<StudentDto>> getUsers() {
//        List<Student> students = studentService.getAll();
//        List<StudentDto> studentDtos = StudentMapper.toListDTO(students);
//        return ResponseEntity.ok(studentDtos);
//    }
//}
