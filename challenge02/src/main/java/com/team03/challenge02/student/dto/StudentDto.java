package com.team03.challenge02.student.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.roles.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;


public record StudentDto (

     Long id,
     @NotBlank(message = "First name is mandatory")
     @Pattern(regexp = "^[A-Z].*", message = "First name must start with an uppercase letter")
     String firstName,
     @NotBlank(message = "First name is mandatory")
     @Pattern(regexp = "^[A-Z].*", message = "Last name must start with an uppercase letter")
     String lastName,
     @NotBlank(message = "Email is mandatory")
     @Email(message = "Email should be valid")
     @Pattern(regexp = "^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,}$")
     String email,
     @JsonFormat(pattern = "yyyy-MM-dd")
     LocalDate birthDate,
     Long course,
     String adress,
     String role

)
{}
