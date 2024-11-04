package com.team03.challenge02.discipline.entity;


import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.student.entity.Student;
import com.team03.challenge02.teacher.entity.Teacher;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name ="tb_disciplines")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Discipline implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column (name = "description", nullable = false)
    private String description;

    @JoinColumn(name = "full_teacher_id", nullable = false)
    @ManyToOne
    private Teacher fullTeacher;
    @JoinColumn(name = "substitute_teacher_id", nullable = false)
    @ManyToOne
    private Teacher substituteTeacher;

    @JoinTable(
            name = "tb_discipline_student", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "discipline_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @ManyToMany
    @Size(max = 10)
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
