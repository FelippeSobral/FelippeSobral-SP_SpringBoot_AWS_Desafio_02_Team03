package com.team03.challenge02.student.repository;

import com.team03.challenge02.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
