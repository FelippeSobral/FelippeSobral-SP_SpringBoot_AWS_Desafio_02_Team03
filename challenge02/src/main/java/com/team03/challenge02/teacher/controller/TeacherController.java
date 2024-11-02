package com.team03.challenge02.teacher.controller;

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
        System.out.println("JSON Body: " + teacherDTO);
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

    /*@PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.email(),
                            loginRequest.password()
                    )
            );

            Set<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

            String token = JwtUtil.generateToken(authentication.getName(), roles);
            return ResponseEntity.ok(Map.of("token", token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }
    }*/

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDTO> login(@RequestBody @Valid LoginRequest loginRequest) {
        RecoveryJwtTokenDTO token = teacherService.authenticateUser(loginRequest);
        return ResponseEntity.ok(token);
    }

}
