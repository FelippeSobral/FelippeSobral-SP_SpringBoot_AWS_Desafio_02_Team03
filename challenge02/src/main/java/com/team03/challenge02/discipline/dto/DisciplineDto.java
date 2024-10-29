package com.team03.challenge02.discipline.dto;

import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.student.entity.Student;
import com.team03.challenge02.teacher.entity.Teacher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Discipline}
 */
@Value
public class DisciplineDto implements Serializable {
    Long id;
    @NotNull
    @NotBlank
    String name;
    @NotNull
    @NotBlank
    String discription;
    @NotNull
    Teacher fullTeacher;
    @NotNull
    Teacher substituteTeacher;
    @NotNull
    @Size
    List<Student> students;
}