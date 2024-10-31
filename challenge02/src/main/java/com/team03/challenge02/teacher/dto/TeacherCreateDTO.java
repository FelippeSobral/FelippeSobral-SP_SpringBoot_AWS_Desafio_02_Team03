package com.team03.challenge02.teacher.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record TeacherCreateDTO(@NotBlank(message = "First name is mandatory") @Pattern(regexp = "^[A-Z].*", message = "First name must start with an uppercase letter") String firstName,
                               @NotBlank(message = "Last name is mandatory") @Pattern(regexp = "^[A-Z].*", message = "Last name must start with an uppercase letter") String lastName,
                               @NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String email,
                               @NotNull(message = "Birth date is mandatory") @Past(message = "Birth date must be in the past") @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Birth date must be in the format YYYY-MM-DD") LocalDate birthDate) {
}
