package com.team03.challenge02.coordinator.entity;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.person.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, of = "id")
public class Coordinator extends Person implements Serializable {

    private Course course;

    public Coordinator() {
    }

    public Coordinator(String firstName, String lastName, String email, LocalDate birthDate, Course course) {
        super(firstName, lastName, email, birthDate);
        this.course = course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


}
