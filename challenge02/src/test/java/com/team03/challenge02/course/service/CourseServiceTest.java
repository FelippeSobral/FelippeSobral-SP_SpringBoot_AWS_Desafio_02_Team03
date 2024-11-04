package com.team03.challenge02.course.service;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.course.repository.CourseRepository;
import com.team03.challenge02.exception.EntityIdNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseServiceTest {

    @Mock
    private CourseRepository repository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldReturnCourse() {
        Course course = new Course();
        course.setName("Math 101");

        when(repository.save(any(Course.class))).thenReturn(course);

        Course result = courseService.create(course);

        assertNotNull(result);
        verify(repository).save(course);
    }

    @Test
    void findById_ShouldReturnCourse() {
        Long id = 1L;
        Course course = new Course();
        course.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(course));

        Course result = courseService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void findById_ShouldThrowEntityIdNotFoundException() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityIdNotFoundException.class, () -> courseService.findById(id));
    }

    @Test
    void findByNameContaining_ShouldReturnCourse() {
        String name = "Math";
        Course course = new Course();
        course.setName(name);

        when(repository.findByNameContaining(name)).thenReturn(course);

        Course result = courseService.findByNameContaining(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
    }

    @Test
    void update_ShouldReturnUpdatedCourse() {
        Long id = 1L;
        Course existingCourse = new Course();
        existingCourse.setId(id);
        existingCourse.setName("Math 101");

        Course updatedCourse = new Course();
        updatedCourse.setName("Math 102");

        when(repository.findById(id)).thenReturn(Optional.of(existingCourse));
        when(repository.save(existingCourse)).thenReturn(existingCourse);

        Course result = courseService.update(id, updatedCourse);

        assertNotNull(result);
        assertEquals("Math 102", result.getName());
        verify(repository).save(existingCourse);
    }

    @Test
    void delete_ShouldInvokeRepositoryDeleteById() {
        Long id = 1L;

        courseService.delete(id);

        verify(repository).deleteById(id);
    }
}
