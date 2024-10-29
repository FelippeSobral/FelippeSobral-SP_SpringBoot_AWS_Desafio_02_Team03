package com.team03.challenge02.teacher.entity;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher extends Person implements Serializable {

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "discipline") //Esperar criação dos atributos da classe Discipline para corrigir o mappedBy
    private List<Discipline> holdingSubjects;

    @OneToMany(mappedBy = "discipline") //Esperar criação dos atributos da classe Discipline para corrigir o mappedBy
    private List<Discipline> substituteSubjects;

}
