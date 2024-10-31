package com.team03.challenge02.startup;

import com.team03.challenge02.course.service.CourseService;
import com.team03.challenge02.student.service.StudentService;
import com.team03.challenge02.teacher.entity.Teacher;
import com.team03.challenge02.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.team03.challenge02.roles.Role.ROLE_TEACHER;

@Component
public class ConfigTest implements CommandLineRunner {

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @Override
    public void run(String... args) throws Exception {

    }
}
