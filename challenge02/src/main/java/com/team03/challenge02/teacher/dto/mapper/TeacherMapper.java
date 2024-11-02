package com.team03.challenge02.teacher.dto.mapper;

import com.team03.challenge02.teacher.dto.TeacherDTO;
import com.team03.challenge02.teacher.entity.Teacher;

public class TeacherMapper {

    public Teacher toEntity(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDTO.firstName());
        teacher.setLastName(teacherDTO.lastName());
        teacher.setEmail(teacherDTO.email());
        teacher.setBirthDate(teacherDTO.birthDate());
        teacher.setPassword(teacherDTO.password());
        return teacher;
    }
}
