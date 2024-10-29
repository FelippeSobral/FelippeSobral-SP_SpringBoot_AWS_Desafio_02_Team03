package com.team03.challenge02.discipline.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name ="tb_disciplines")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
