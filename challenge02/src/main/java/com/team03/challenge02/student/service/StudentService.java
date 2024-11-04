package com.team03.challenge02.student.service;

import com.team03.challenge02.exception.AdressInvalidException;
import com.team03.challenge02.security.JwtTokenService;
import com.team03.challenge02.security.UserDetailsImpl;
import com.team03.challenge02.student.dto.StudentDto;
import com.team03.challenge02.student.dto.mapper.StudentMapper;
import com.team03.challenge02.student.entity.Student;

import com.team03.challenge02.student.repository.StudentRepository;
import com.team03.challenge02.student.viacep.ViaCepClient;
import com.team03.challenge02.teacher.dto.LoginRequest;
import com.team03.challenge02.teacher.dto.RecoveryJwtTokenDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    private final ViaCepClient viaCepClient;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;


    @Transactional
    public Student save(StudentDto studentDto) {
        Student student = StudentMapper.toStudent(studentDto);

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

    @Transactional
    public void delete(long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
    public RecoveryJwtTokenDTO authenticateUser(@Valid LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new RecoveryJwtTokenDTO(jwtTokenService.generateToken(userDetails));
    }
}
