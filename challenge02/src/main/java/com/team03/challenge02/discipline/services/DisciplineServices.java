package com.team03.challenge02.discipline.services;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.course.repository.CourseRepository;
import com.team03.challenge02.discipline.dto.DisciplineDto;
import com.team03.challenge02.discipline.dto.mapper.DisciplineMapper;
import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.discipline.repository.DisciplineRepository;
import com.team03.challenge02.student.entity.Student;
import com.team03.challenge02.student.repository.StudentRepository;
import com.team03.challenge02.teacher.entity.Teacher;
import com.team03.challenge02.teacher.repository.TeacherRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DisciplineServices {

    private final DisciplineRepository disciplineRepository;
    private final DisciplineMapper disciplineMapper;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public DisciplineServices(DisciplineRepository disciplineRepository, DisciplineMapper disciplineMapper,CourseRepository courseRepository,TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.disciplineRepository = disciplineRepository;
        this.disciplineMapper = disciplineMapper;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public List<Discipline> findAll(){
        return disciplineRepository.findAll();
    }
    @Transactional
    public DisciplineDto createDiscipline(DisciplineDto disciplineDto) {
        Discipline discipline = disciplineMapper.toEntity(disciplineDto);
        Discipline savedDiscipline = disciplineRepository.save(discipline);
        return disciplineMapper.toDto(savedDiscipline);
    }
    @Transactional
    public DisciplineDto updateDiscipline(Long id, DisciplineDto dto) {
        Discipline existingDiscipline = disciplineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discipline not found with id: " + id));

        existingDiscipline.setName(dto.name());
        existingDiscipline.setDescription(dto.description());

        if (dto.courseId() != null) {
            Course course = courseRepository.findById(dto.courseId().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Course not found"));
            existingDiscipline.setCourse(course);
        }

        if (dto.fullTeacherId() != null) {
            Teacher fullTeacher = teacherRepository.findById(dto.fullTeacherId().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Main teacher not found"));
            existingDiscipline.setFullTeacher(fullTeacher);
        }

        if (dto.substituteTeacherId() != null) {
            Teacher substituteTeacher = teacherRepository.findById(dto.substituteTeacherId().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Substitute teacher not found"));
            existingDiscipline.setSubstituteTeacher(substituteTeacher);
        }

        Set<Student> students = dto.studentIds().stream()
                .map(( idStudent) -> studentRepository.findById(idStudent.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + idStudent)))
                .collect(Collectors.toSet());
        existingDiscipline.setStudents((List<Student>) students);

        return DisciplineMapper.toDto(disciplineRepository.save(existingDiscipline));
    }
    public Optional<Discipline> getById(long id) {
        return disciplineRepository.findById(id);
    }
    public void delete(Long id){
        disciplineRepository.deleteById(id);
    }
}
