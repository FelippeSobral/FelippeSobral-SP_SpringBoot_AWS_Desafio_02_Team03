
package com.team03.challenge02.registration.entities;


import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.student.entity.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "student_id" , nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id" , nullable = false)
    private Course course;

}