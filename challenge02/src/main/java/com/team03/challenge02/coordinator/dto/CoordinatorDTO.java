package com.team03.challenge02.coordinator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  @Setter  @AllArgsConstructor  @NoArgsConstructor
public class CoordinatorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String disciplinas;
    private String course;

}
