package com.team03.challenge02.discipline.entity;

import com.team03.challenge02.student.entity.Student;
import com.team03.challenge02.teacher.entity.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name ="tb_disciplines")
public class Discipline implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column (name = "discription", nullable = false)
    private String discription;

    @JoinColumn(name = "full_teacher_id")
    @ManyToOne
    private Teacher fullTeacher;
    @JoinColumn(name = "substitute_teacher_id")
    @ManyToOne
    private Teacher substituteTeacher;
    @JoinColumn (name = "student_id")
    @OneToMany
    private List<Student> students = new ArrayList<>();


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
