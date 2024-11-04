package com.team03.challenge02.teacher.common;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.teacher.dto.TeacherDTO;
import com.team03.challenge02.teacher.entity.Teacher;

import java.time.LocalDate;
import java.util.List;

public class TeacherConstants {

    public static final TeacherDTO TEACHER = new TeacherDTO("nome", "lastname", "felipe@email.com", LocalDate.of(2000,10,14), "password");
}
