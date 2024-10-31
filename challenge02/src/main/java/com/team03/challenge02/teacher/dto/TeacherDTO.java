package com.team03.challenge02.teacher.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record TeacherDTO(@NotBlank(message = "First name is mandatory") @Pattern(regexp = "^[A-Z].*", message = "First name must start with an uppercase letter") String firstName,
                         @NotBlank(message = "Last name is mandatory") @Pattern(regexp = "^[A-Z].*", message = "Last name must start with an uppercase letter") String lastName,
                         @NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") @Pattern(regexp = "^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,}$", message = "Email must follow the format: usuario@dominio.com")String email,
                         @NotNull(message = "Birth date is mandatory") @Past(message = "Birth date must be in the past") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthDate) {
}
