package com.team03.challenge02.student.entity;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.person.Person;
import com.team03.challenge02.roles.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.team03.challenge02.roles.Role.ROLE_STUDENT;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name ="tb_students")
public class Student extends Person {

    @OneToOne
    @JoinColumn(name ="course_id")
    private Course course;

    private String adress;

    @Enumerated(EnumType.STRING)
    public Role role = ROLE_STUDENT;
    @Override
    public String toString() {
        return "Student {course=" + course +
                ", adress=" + adress + "}"
                +super.toString();
    }
}
