package com.team03.challenge02.student.service;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.course.repository.CourseRepository;
import com.team03.challenge02.security.JwtTokenService;
import com.team03.challenge02.security.UserDetailsImpl;
import com.team03.challenge02.student.dto.StudentDto;
import com.team03.challenge02.student.dto.mapper.StudentMapper;
import com.team03.challenge02.student.entity.Student;
import com.team03.challenge02.student.exception.AdressInvalidException;
import com.team03.challenge02.student.repository.StudentRepository;
import com.team03.challenge02.student.viacep.ViaCepClient;
import com.team03.challenge02.teacher.dto.LoginRequest;
import com.team03.challenge02.teacher.dto.RecoveryJwtTokenDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ViaCepClient viaCepClient;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    /*@Autowired
    public StudentService(StudentRepository studentRepository, ViaCepClient viaCepClient, CourseRepository courseRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.viaCepClient = viaCepClient;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }*/

    @Transactional
    public Student save2(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    @Transactional
    public Student save(StudentDto studentDto) {
        Course course = courseRepository.findById(studentDto.course().getId()).orElseThrow(EntityNotFoundException::new);
        Student student = StudentMapper.toStudent(studentDto,course);

        try { if (student.getAdress() != null) {
            var adress = viaCepClient.getAdress(student.getAdress());
            String completeAdress = adress.street() + ","
                    + adress.neighborhood() + ","
                    + adress.city() + "," + adress.state();
            student.setAdress(completeAdress); } }
        catch (AdressInvalidException e) {
            throw new EntityNotFoundException("Invalid address"); }
        if (!isOldEnough(student.getBirthDate())) {
            throw new IllegalArgumentException("The student must be at least 18 years old."); }
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    private boolean isOldEnough(LocalDate birthdate) {
        if (birthdate == null) {
            return false;
        }
        return Period.between(birthdate, LocalDate.now()).getYears() >= 18;
    }
    @Transactional(readOnly = true)
    public Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Student not found with id " + id));
    }
    @Transactional(readOnly = true)
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public RecoveryJwtTokenDTO authenticateUser(@Valid LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new RecoveryJwtTokenDTO(jwtTokenService.generateToken(userDetails));
    }
}
