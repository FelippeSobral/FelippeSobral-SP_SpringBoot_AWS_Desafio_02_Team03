package com.team03.challenge02.student.dto.mapper;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.roles.Role;
import com.team03.challenge02.student.dto.StudentDto;
import com.team03.challenge02.student.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {

    public static Student toStudent(StudentDto studentDto) {
        if (studentDto == null) {
            return null;
        }

        Student student = new Student();
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setEmail(studentDto.email());
        student.setBirthDate(studentDto.birthDate());
        student.setAdress(studentDto.adress());
        student.setPassword(studentDto.password());

        return student;
    }

    public static StudentDto toStudentDto(Student student) {
        if (student == null) {
            return null;
        }
        Course course = student.getCourse();
        return  new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getBirthDate(),
                student.getAdress(),
                student.getPassword()
        );
    }

    public static List<StudentDto> toListDTO(List<Student> userEntities) {
        return userEntities.stream()
                .map(StudentMapper::toStudentDto)
                .collect(Collectors.toList());
    }
}
