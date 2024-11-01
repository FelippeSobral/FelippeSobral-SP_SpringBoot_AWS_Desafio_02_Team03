//package com.team03.challenge02.registration.repository;
//
//import com.team03.challenge02.course.entity.Course;
//import com.team03.challenge02.registration.entities.Registration;
//import com.team03.challenge02.student.entity.Student;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//
//@Repository
//public interface IRegistrationRepository extends JpaRepository<Registration, Long> {
//    Optional<Registration> findByStudentAndCourse(Student student, Course course);
//}
