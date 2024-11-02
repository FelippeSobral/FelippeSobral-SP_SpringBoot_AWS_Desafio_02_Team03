//package com.team03.challenge02.student.service;
//
//import com.team03.challenge02.course.entity.Course;
//import com.team03.challenge02.course.repository.CourseRepository;
//import com.team03.challenge02.student.dto.StudentDto;
//import com.team03.challenge02.student.dto.mapper.StudentMapper;
//import com.team03.challenge02.student.entity.Student;
//import com.team03.challenge02.student.exception.AdressInvalidException;
//import com.team03.challenge02.student.repository.StudentRepository;
//import com.team03.challenge02.student.viacep.ViaCepClient;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//
//import java.time.LocalDate;
//import java.time.Period;
//import java.util.List;
//
//@Service
//
//public class StudentService {
//
//    private final StudentRepository studentRepository;
//    private final CourseRepository courseRepository;
//    private ViaCepClient viaCepClient;
//
//    public StudentService(StudentRepository studentRepository, ViaCepClient viaCepClient, CourseRepository courseRepository) {
//        this.studentRepository = studentRepository;
//        this.viaCepClient = viaCepClient;
//        this.courseRepository = courseRepository;
//    }
//
//
//    @Transactional
//    public Student save(StudentDto studentDto) {
//        Course course = courseRepository.findById(studentDto.id()).orElseThrow(EntityNotFoundException::new);
//        Student student = StudentMapper.toStudent(studentDto,course);
//
//        try { if (student.getAdress() != null) {
//            var adress = viaCepClient.getAdress(student.getAdress());
//            String completeAdress = adress.street() + ","
//                    + adress.neighborhood() + ","
//                    + adress.city() + "," + adress.state();
//            student.setAdress(completeAdress); } }
//        catch (AdressInvalidException e) {
//            throw new EntityNotFoundException("Invalid address"); }
//        if (!isOldEnough(student.getBirthDate())) {
//            throw new IllegalArgumentException("The student must be at least 18 years old."); }
//        return studentRepository.save(student);
//    }
//    private boolean isOldEnough(LocalDate birthdate) {
//        if (birthdate == null) {
//            return false;
//        }
//        return Period.between(birthdate, LocalDate.now()).getYears() >= 18;
//    }
//    @Transactional(readOnly = true)
//    public Student getById(Long id) {
//        return studentRepository.findById(id).orElseThrow(()
//                -> new EntityNotFoundException("Student not found with id " + id));
//    }
//    @Transactional(readOnly = true)
//    public List<Student> getAll() {
//        return studentRepository.findAll();
//    }
//}
