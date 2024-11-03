package com.team03.challenge02.coordinator.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassworDTO {

    @NotBlank
    @Length(min = 6, max = 10)
    private String currentPassword;
    @NotBlank
    @Length(min = 6, max = 10)
    private String newPassword;
    @NotBlank
    @Length(min = 6, max = 10)
    private String repetePassword;
}
