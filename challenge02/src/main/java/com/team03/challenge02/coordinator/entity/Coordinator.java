package com.team03.challenge02.coordinator.entity;

import com.team03.challenge02.person.Person;
import com.team03.challenge02.roles.Role;
import com.team03.challenge02.security.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coordinator")
public class Coordinator extends Person implements Serializable, UserEntity {

    @Column(name = "disciplinas")
    private String disciplinas;
    @Column(name = "course")
    private String course;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_COORDINATOR;

}
