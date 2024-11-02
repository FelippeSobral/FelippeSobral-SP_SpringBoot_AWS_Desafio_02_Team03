package com.team03.challenge02.teacher.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        @Pattern(regexp = "^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,}$", message = "Email must follow the format: usuario@dominio.com")
        String email,
        @NotBlank(message = "Password is mandatory.")
        String password
) {
}
