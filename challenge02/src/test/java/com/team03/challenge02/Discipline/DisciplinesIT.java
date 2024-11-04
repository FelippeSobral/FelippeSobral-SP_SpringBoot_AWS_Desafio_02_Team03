package com.team03.challenge02.Discipline;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.course.repository.CourseRepository;
import com.team03.challenge02.discipline.dto.DisciplineDto;
import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.discipline.repository.DisciplineRepository;
import com.team03.challenge02.discipline.services.DisciplineServices;
import com.team03.challenge02.student.repository.StudentRepository;
import com.team03.challenge02.teacher.repository.TeacherRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class DisciplinesIT {
    @Mock
    private DisciplineRepository disciplineRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private DisciplineServices disciplineService;

    private Discipline discipline;
    private DisciplineDto disciplineDto;
    private Course course;

    @BeforeEach
    void setUp() {
        course = new Course(1L, "Computer Science", "Bachelor of Computer Science", null, new ArrayList<>());
        discipline = new Discipline(1L, "Math", "Advanced Math", null, null, new ArrayList<>(), course);
        disciplineDto = new DisciplineDto(1L, "Math", "Advanced Math", null, null, new ArrayList<>(), course);
    }

    @Test
    void shouldReturnAllDisciplines() {
        when(disciplineRepository.findAll()).thenReturn(List.of(discipline));

        List<Discipline> result = disciplineService.findAll();

        assertEquals(1, result.size());
        assertEquals(discipline.getName(), result.get(0));

    }

    @Test
    void shouldReturnDisciplineById() {
        when(disciplineRepository.findById(1L)).thenReturn(Optional.of(discipline));

        Optional<Discipline> result = disciplineService.getById(1L);

        assertNotNull(result);
        assertEquals(discipline.getName(), result.get());

    }

    @Test
    void shouldCreateDiscipline() {
        when(disciplineRepository.save(any(Discipline.class))).thenReturn(discipline);

        DisciplineDto result = disciplineService.createDiscipline(disciplineDto);

        assertNotNull(result);
        assertEquals(discipline.getName(), result.name());

    }

    @Test
    void shouldUpdateDiscipline() {
        when(disciplineRepository.findById(1L)).thenReturn(Optional.of(discipline));
        when(disciplineRepository.save(any(Discipline.class))).thenReturn(discipline);

        DisciplineDto result = disciplineService.updateDiscipline(1L, disciplineDto);

        assertNotNull(result);
        assertEquals(disciplineDto.name(), result.name());

    }

    @Test
    void shouldDeleteDiscipline() {

        when(disciplineRepository.findById(1L)).thenReturn(Optional.of(discipline));
        doNothing().when(disciplineRepository).delete(discipline);

        disciplineService.delete(1L);


    }
}
