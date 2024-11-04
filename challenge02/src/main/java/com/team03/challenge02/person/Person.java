package com.team03.challenge02.person;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The field cant be blank")
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    @NotBlank(message = "The field cant be blank")
    private String lastName;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "The field cant be blank")
    private String email;
    @Column(nullable = false)
    @NotNull
    private LocalDate birthDate;

    public Person(String firstName, String lastName, String email, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }
}
