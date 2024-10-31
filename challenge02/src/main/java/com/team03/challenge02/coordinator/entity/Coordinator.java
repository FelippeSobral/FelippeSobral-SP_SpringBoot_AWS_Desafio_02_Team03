package com.team03.challenge02.coordinator.entity;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name ="tb_coordinators")

public class Coordinator extends Person {

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Coordinator(long id, @NotBlank(message = "The field cant be blank") String firstName, @NotBlank(message = "The field cant be blank") String lastName, @NotBlank(message = "The field cant be blank") String email, @NotNull LocalDate birthDate, Course course) {
        super(id, firstName, lastName, email, birthDate);
        this.course = course;
    }
}
