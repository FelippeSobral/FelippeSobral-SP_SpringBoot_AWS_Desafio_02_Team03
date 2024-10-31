package com.team03.challenge02.teacher;

import com.team03.challenge02.teacher.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TeacherService.class)
public class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @Test
    public void createTeacher_WithValidData_ReturnsTeacher() {
        teacherService.create()

    }
}
