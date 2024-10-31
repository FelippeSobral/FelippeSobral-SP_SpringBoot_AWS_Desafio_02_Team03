package com.team03.challenge02.student.service;

import com.team03.challenge02.student.dto.StudentDto;
import com.team03.challenge02.student.entity.Student;
import com.team03.challenge02.student.repository.StudentRepository;
import com.team03.challenge02.student.viacep.ViaCepClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private ViaCepClient viaCepClient;

    @Transactional
    public Student save(Student student) {
        if(student.getAdress() != null) {
         var adress = viaCepClient.getAdress(student.getAdress());
         String completeAdress = adress.street() + "," + adress.neighborhood()
                                + "," + adress.city() + "," + adress.state();
         student.setAdress(completeAdress);
        }

        if (!isOldEnough(student.getBirthDate())) {
            throw new IllegalArgumentException("The student must be at least 18 years old.");
        }

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
}
