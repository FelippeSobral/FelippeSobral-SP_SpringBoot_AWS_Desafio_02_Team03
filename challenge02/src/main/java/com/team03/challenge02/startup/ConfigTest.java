package com.team03.challenge02.startup;

import com.team03.challenge02.coordinator.entity.Coordinator;
import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.course.service.CourseService;
import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.discipline.services.DisciplineServices;
//import com.team03.challenge02.student.service.StudentService;
import com.team03.challenge02.teacher.entity.Teacher;
import com.team03.challenge02.teacher.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

import static com.team03.challenge02.roles.Role.ROLE_STUDENT;
import static com.team03.challenge02.roles.Role.ROLE_TEACHER;

@Component
@Slf4j
public class ConfigTest implements CommandLineRunner {

    @Autowired
    TeacherService teacherService;

   // @Autowired
   //StudentService studentService;

    @Autowired
    CourseService courseService;

    @Autowired
    DisciplineServices disciplineService;

    @Override
    public void run(String... args) throws Exception {
//        try {
//
//            Optional<Teacher> optionalSubTeacher = teacherService.getById(2);
//            Optional<Teacher> optionalFullTeacher = teacherService.getById(1);
//            Teacher fullTeacher = optionalFullTeacher.get();
//
//            Teacher substituteTeacher = optionalSubTeacher.get();
//
//            Optional<Course> courseOpt = Optional.ofNullable(courseService.findById(2));
//            Course course = courseOpt.get();
//
//            fullTeacher.setCourse(course);
//
//            Optional<Discipline> disciplineOpt = disciplineService.getById(2);
//            Discipline discipline = disciplineOpt.get();
//
//            discipline.setCourse(course);
//            log.info("discipline list: {}", course.getDisciplinesList());
//
//            course.getDisciplinesList().add(discipline);
//
//
//            courseService.create(course);
//            teacherService.create(fullTeacher);
//
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
    }
}
