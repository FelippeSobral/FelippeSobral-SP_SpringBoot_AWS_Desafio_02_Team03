package com.team03.challenge02.coordinator.entity;

import com.team03.challenge02.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coordinator")
public class Coordinator extends Person implements Serializable {

    @Column(name = "disciplinas")
    private String disciplinas;
    @Column(name = "course")
    private String course;
}
