package com.team03.challenge02.student.entity;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name ="tb_students")
public class Student extends Person {
    private Course course;
    private String adress;

}
