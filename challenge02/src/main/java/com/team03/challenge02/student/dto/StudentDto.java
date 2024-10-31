package com.team03.challenge02.student.dto;


import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.roles.Role;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
public class StudentDto {

    private Long id;

    @Pattern(regexp = "^[A-Z].*", message = "First name must start with an uppercase letter")
    private String firstName;

    @Pattern(regexp = "^[A-Z].*", message = "Last name must start with an uppercase letter")
    private String lastName;

    private String email;
    private LocalDate birthDate;
    private String course;
    private String adress;
    private String role;


}
