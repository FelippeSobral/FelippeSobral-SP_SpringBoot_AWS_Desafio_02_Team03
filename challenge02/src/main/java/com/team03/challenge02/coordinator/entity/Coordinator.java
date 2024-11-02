package com.team03.challenge02.coordinator.entity;

import com.team03.challenge02.person.Person;
import com.team03.challenge02.roles.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import static com.team03.challenge02.roles.Role.ROLE_COORDINATOR;
import static com.team03.challenge02.roles.Role.ROLE_TEACHER;


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
    @Enumerated(EnumType.STRING)
    private Role role = ROLE_COORDINATOR;

}
