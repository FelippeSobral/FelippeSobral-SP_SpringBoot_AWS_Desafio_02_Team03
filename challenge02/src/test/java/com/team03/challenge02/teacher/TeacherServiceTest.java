package com.team03.challenge02.teacher;

import com.team03.challenge02.teacher.dto.mapper.TeacherMapper;
import com.team03.challenge02.teacher.entity.Teacher;
import com.team03.challenge02.teacher.repository.TeacherRepository;
import com.team03.challenge02.teacher.service.TeacherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import static com.team03.challenge02.teacher.common.TeacherConstants.TEACHER;
import static org.mockito.Mockito.when;

@SpringBootTest()
@Transactional
public class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @MockBean
    private TeacherRepository teacherRepository;

    @Test
    public void createTeacher_WithValidData_ReturnsTeacher() {
        TeacherMapper teacherMapper = new TeacherMapper();
        Teacher teacherEntity =  teacherMapper.toEntity(TEACHER);

        when(teacherRepository.save(Mockito.any())).thenReturn(teacherEntity);

        //system under test
        Teacher sut = teacherService.create(teacherEntity);

        Assertions.assertEquals(sut.getClass(), teacherEntity.getClass());
        Assertions.assertNotNull(sut.getClass());
    }
}
