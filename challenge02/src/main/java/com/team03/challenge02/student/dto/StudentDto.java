package com.team03.challenge02.student.dto;


import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.roles.Role;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private Course course;
    private String adress;
    private String role;
}
