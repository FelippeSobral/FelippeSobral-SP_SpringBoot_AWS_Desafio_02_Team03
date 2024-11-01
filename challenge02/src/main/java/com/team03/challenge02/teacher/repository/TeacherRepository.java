package com.team03.challenge02.teacher.repository;

import com.team03.challenge02.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
