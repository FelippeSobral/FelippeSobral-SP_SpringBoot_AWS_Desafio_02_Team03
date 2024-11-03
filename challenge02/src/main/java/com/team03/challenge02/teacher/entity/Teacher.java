package com.team03.challenge02.teacher.entity;

import com.fasterxml.jackson.annotation.*;
import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.person.Person;
import com.team03.challenge02.roles.Role;
import com.team03.challenge02.security.UserEntity;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Teacher extends Person implements Serializable, UserEntity {

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "fullTeacher")

    private List<Discipline> holdingSubjects = new ArrayList<>();

    @OneToMany(mappedBy = "substituteTeacher")

    private List<Discipline> substituteSubjects = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role = ROLE_TEACHER;

    @JsonIgnore
    private String password;

    public Teacher() {
    }

    public Teacher(
            String firstName,
            String lastName,
            String email,
            LocalDate birthDate,
            String password,
            Course course,
            List<Discipline> holdingSubjects,
            List<Discipline> substituteSubjects) {
        super(firstName, lastName, email, birthDate);
        this.course = course;
        this.holdingSubjects = holdingSubjects;
        this.substituteSubjects = substituteSubjects;
    }

    public Teacher(
            String firstName,
            String lastName,
            String email,
            LocalDate birthDate,
            String password) {
        super(firstName, lastName, email, birthDate);
        this.password = password;
    }
}

