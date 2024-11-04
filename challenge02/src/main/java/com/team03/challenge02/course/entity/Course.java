package com.team03.challenge02.course.entity;

import com.team03.challenge02.coordinator.entity.Coordinator;
import com.team03.challenge02.discipline.entity.Discipline;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(of="id")
@Entity
@Table(name ="tb_courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "coordinator")
    private String coordinator;
    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER)
    @Column(name = "disciplines_id")
    private List<Discipline> disciplinesList = new ArrayList<>();


}
