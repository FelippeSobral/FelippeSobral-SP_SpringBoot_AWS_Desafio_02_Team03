package com.team03.challenge02.registration.Services;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.course.repository.CourseRepository;
import com.team03.challenge02.registration.dto.RegistrationDTO;
import com.team03.challenge02.registration.entities.Registration;
import com.team03.challenge02.registration.repository.IRegistrationRepository;
import com.team03.challenge02.student.entity.Student;
import com.team03.challenge02.student.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServices {

    @Autowired
    private IRegistrationRepository registrationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public RegistrationDTO createRegistration(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found"));
        registrationRepository.findByStudentAndCourse(student, course).ifPresent(m -> {throw new IllegalArgumentException("Student is already enrolled"); });

        Registration registration = new Registration();

        registration.setStudant(student);

        registration.setCourse(course);

        registrationRepository.save(registration);

        return new RegistrationDTO(registration.getId(), studentId, courseId);
    }

    public List<RegistrationDTO> listRegistrationsByStudent(Long studentId) {
        return registrationRepository.findAllByStudentId(studentId)
                .stream()
                .map(registration -> new RegistrationDTO(registration.getId(), studentId, registration.getCourse().getId()))
                .collect(Collectors.toList());
    }

    public void deleteRegistration(Long registrationId) {
        if (!registrationRepository.existsById(registrationId)) {
            throw new EntityNotFoundException("Registration not found");
        }
        registrationRepository.deleteById(registrationId);
    }
}
