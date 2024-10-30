package com.team03.challenge02.course.entity;

import com.team03.challenge02.coordinator.entity.Coordinator;
import com.team03.challenge02.discipline.entity.Discipline;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor  @EqualsAndHashCode(of="id")
@Entity
@Table(name ="tb_courses")

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @JoinColumn(nullable = false)
    @OneToOne(mappedBy = "course")
    private Coordinator coordinator;
    @OneToMany
    @JoinColumn(name = "course_id")
    private List<Discipline> disciplinesList;

}
