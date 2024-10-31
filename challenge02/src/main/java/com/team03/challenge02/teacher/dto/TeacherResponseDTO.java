package com.team03.challenge02.teacher.dto;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.discipline.entity.Discipline;

import java.time.LocalDate;
import java.util.List;

public record TeacherResponseDTO(String firstName, String lastName, String email, LocalDate birthDate, Course course, List<Discipline> holdingSubjects, List<Discipline> substituteSubjects) {
}
