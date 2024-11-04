package com.team03.challenge02.registration;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.course.repository.CourseRepository;
import com.team03.challenge02.registration.Services.RegistrationServices;
import com.team03.challenge02.registration.dto.RegistrationDTO;
import com.team03.challenge02.registration.entities.Registration;
import com.team03.challenge02.registration.repository.IRegistrationRepository;
import com.team03.challenge02.student.entity.Student;
import com.team03.challenge02.student.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RegistrationServicesTest {

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private RegistrationServices registrationServices;

    private Student student;
    private Course course;
    private Registration registration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student = new Student();
        student.setId(1L);

        course = new Course();
        course.setId(1L);

        registration = new Registration();
        registration.setId(1L);
        registration.setStudent(student);
        registration.setCourse(course);
        }

        @Test
        void createRegistration_ShouldReturnRegistrationDTO() {

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(registrationRepository.findByStudentAndCourse(student , course )).thenReturn(Optional.empty());
        when(registrationRepository.save(registration)).thenReturn(registration);

         RegistrationDTO result = registrationServices.createRegistration(1L,1L);

         assertThat(result).isNotNull();
         assertThat(result.student_id()).isEqualTo(1L);
         assertThat(result.course_id()).isEqualTo(1L);

         verify(registrationRepository).save(registration);
        }

        @Test
        void createRegistration_ShouldThrowException_WhenStudentIdIsNull() {

            when(studentRepository.findById(1L)).thenReturn(Optional.empty());
            assertThatThrownBy(() -> registrationServices.createRegistration(1L, 1L))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Student not found");
        }

    @Test
    void createRegistration_ShouldThrowEntityNotFoundException_WhenCourseNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> registrationServices.createRegistration(1L, 1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Course not found");

}

    void createRegistration_ShouldThrowIllegalArgumentException_WhenStudentAlreadyEnrolled() {

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(registrationRepository.findByStudentAndCourse(student, course)).thenReturn(Optional.of(registration));

        assertThatThrownBy(() -> registrationServices.createRegistration(1L, 1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Student is already enrolled");
    }

    @Test
    void listRegistrationsByStudent_ShouldReturnListOfRegistrationDTOs() {

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(registrationRepository.findAllByStudentId(1L)).thenReturn(List.of(registration));

        List<RegistrationDTO> result = registrationServices.listRegistrationsByStudent(1L);

        assertThat(result);
        assertThat(result.get(0).student_id()).isEqualTo(1L);
        assertThat(result.get(0).course_id()).isEqualTo(1L);
    }

    @Test
    void listRegistrationsByStudent_ShouldThrowEntityNotFoundException_WhenStudentNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> registrationServices.listRegistrationsByStudent(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Student not found");
    }







}