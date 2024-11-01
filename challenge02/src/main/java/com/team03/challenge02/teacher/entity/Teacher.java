package com.team03.challenge02.teacher.entity;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.person.Person;
import com.team03.challenge02.roles.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.team03.challenge02.roles.Role.ROLE_TEACHER;


@Getter
@Setter
@Entity
@Table(name = "tb_teachers")
public class Teacher extends Person implements Serializable {

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "fullTeacher")
    private List<Discipline> holdingSubjects = new ArrayList<>();

    @OneToMany(mappedBy = "substituteTeacher")
    private List<Discipline> substituteSubjects = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role = ROLE_TEACHER;

    public Teacher() {
    }

    public Teacher(
            String firstName,
            String lastName,
            String email,
            LocalDate birthDate,
            Course course,
            List<Discipline> holdingSubjects,
            List<Discipline> substituteSubjects) {
        super(firstName, lastName, email, birthDate);
        this.course = course;
        this.holdingSubjects = holdingSubjects;
        this.substituteSubjects = substituteSubjects;
    }


}

