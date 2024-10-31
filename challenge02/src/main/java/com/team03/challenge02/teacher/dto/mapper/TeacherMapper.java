package com.team03.challenge02.teacher.dto.mapper;

import com.team03.challenge02.teacher.dto.TeacherCreateDTO;
import com.team03.challenge02.teacher.entity.Teacher;

import java.util.ArrayList;

public class TeacherMapper {

    public Teacher toEntity(TeacherCreateDTO teacherCreateDTO) {
        return new Teacher(
                teacherCreateDTO.firstName(),
                teacherCreateDTO.lastName(),
                teacherCreateDTO.email(),
                teacherCreateDTO.birthDate(),
                null,
                new ArrayList<>(),
                new ArrayList<>()
        );

    //public TeacherCreateDTO toCreateDTO(Teacher teacher) {
    }
}
