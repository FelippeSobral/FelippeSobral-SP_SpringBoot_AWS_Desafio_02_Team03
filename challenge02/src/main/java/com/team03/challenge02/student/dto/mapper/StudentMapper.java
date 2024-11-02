package com.team03.challenge02.student.dto.mapper;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.roles.Role;
import com.team03.challenge02.student.dto.StudentDto;
import com.team03.challenge02.student.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {

    public static Student toStudent(StudentDto studentDto, Course course) {
        if (studentDto == null) {
            return null;
        }

        Student student = new Student();
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setEmail(studentDto.email());
        student.setBirthDate(studentDto.birthDate());
        student.setCourse(course);
        student.setAdress(studentDto.adress());
        student.setRole(Role.valueOf("ROLE_" + studentDto.role().toUpperCase()));

        return student;
    }

    public static StudentDto toStudentDto(Student student) {
        if (student == null) {
            return null;
        }

        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getBirthDate(),
                student.getCourse() != null ? student.getCourse().getId() : null,
                student.getAdress(),
                student.getRole().name().substring("ROLE_".length()).toUpperCase()
        );
    }

    public static List<StudentDto> toListDTO(List<Student> userEntities) {
        return userEntities.stream()
                .map(StudentMapper::toStudentDto)
                .collect(Collectors.toList());
    }
}
