package com.team03.challenge02.student;

import com.team03.challenge02.exception.AdressInvalidException;
import com.team03.challenge02.security.JwtTokenService;

import com.team03.challenge02.student.dto.StudentDto;
import com.team03.challenge02.student.entity.Student;
import com.team03.challenge02.student.repository.StudentRepository;
import com.team03.challenge02.student.service.StudentService;
import com.team03.challenge02.student.viacep.Adress;
import com.team03.challenge02.student.viacep.ViaCepClient;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    StudentRepository studentRepositoryMock;

    @Mock PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private ViaCepClient viaCepClient;

    @Mock
    private JwtTokenService jwtTokenService;

    private Student student;
    private StudentDto studentDto;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        student.setAdress("12345678");
        student.setPassword("password123");
        student.setBirthDate(LocalDate.of(2000, 1, 1));

        studentDto = new StudentDto(
                1L,
                "John",
                "Doe",
                "john.doe@example.com",
                LocalDate.of(2000, 1, 1),
                "12345678",
                "password123");

    }

    @Test
    void saveStudent_withValidData_savesStudent() {

        when(viaCepClient.getAdress(anyString())).thenReturn(new Adress("Street", "Neighborhood", "City", "State" , "12345"));
        when(passwordEncoder.encode(anyString())).thenReturn("password123");
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student savedStudent = studentService.save(studentDto);

        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getAdress()).isEqualTo("Street,Neighborhood,City,State");
        assertThat(savedStudent.getPassword()).isEqualTo("password123");
        verify(studentRepository, times(1)).save(any(Student.class));

    }


    @Test
    void saveStudent_withInvalidAddress_throwsEntityNotFoundException() {
        when(viaCepClient.getAdress(anyString())).thenThrow(AdressInvalidException.class);

        assertThatThrownBy(() -> studentService.save(studentDto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Invalid address");
    }

    @Test
    void saveStudent_withUnderageStudent_throwsIllegalArgumentException() {
        studentDto.birthDate();

        assertThatThrownBy(() -> studentService.save(studentDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The student must be at least 18 years old.");
    }


    @Test
    void getById_withExistingStudent_returnsStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student foundStudent = studentService.getById(1L);

        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.getId()).isEqualTo(1L);
        verify(studentRepository).findById(1L);
    }

    @Test
    void getById_withNonExistentStudent_throwsEntityNotFoundException() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.getById(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Student not found with id 1");
    }

    @Test
    void getAll_returnsListOfStudents() {
        when(studentRepository.findAll()).thenReturn(List.of(student));

        List<Student> students = studentService.getAll();

        assertThat(students).hasSize(1);
        assertThat(students.get(0)).isEqualTo(student);
        verify(studentRepository).findAll();
    }

    @Test
    void delete_withExistingStudent_deletesStudent() {
        when(studentRepository.existsById(1L)).thenReturn(true);

        studentService.delete(1L);

        verify(studentRepository).deleteById(1L);
    }

    @Test
    void delete_withNonExistentStudent_throwsIllegalArgumentException() {
        when(studentRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> studentService.delete(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Student not found with id: 1");
    }

}