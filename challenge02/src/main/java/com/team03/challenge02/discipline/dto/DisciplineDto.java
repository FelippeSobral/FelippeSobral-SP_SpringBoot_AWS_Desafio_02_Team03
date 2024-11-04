package com.team03.challenge02.discipline.dto;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.student.entity.Student;
import com.team03.challenge02.teacher.entity.Teacher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;



public record DisciplineDto (
    Long id,
    String name,
    @NotNull
    @NotBlank
    String description,
    @NotNull
    Teacher fullTeacherId,
    @NotNull
    Teacher substituteTeacherId,
    @NotNull
    @Size(max = 10)
    List<Student> studentIds,
    @NotNull
    Course courseId){}