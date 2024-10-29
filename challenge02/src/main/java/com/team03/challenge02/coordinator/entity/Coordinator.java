package com.team03.challenge02.coordinator.entity;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name ="tb_coordinators")

public class Coordinator extends Person {

    private Course course;

}
