package com.team03.challenge02.course.entity;

import com.team03.challenge02.coordinator.entity.Coordinator;
import com.team03.challenge02.discipline.entity.Discipline;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(of="id")
@Entity
@Table(name ="tb_courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",unique = true, nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "coordinator", nullable = true)
    private String coordinator;
    @Column(name = "discipline_id")
    private List<Discipline> disciplinesList;


}
