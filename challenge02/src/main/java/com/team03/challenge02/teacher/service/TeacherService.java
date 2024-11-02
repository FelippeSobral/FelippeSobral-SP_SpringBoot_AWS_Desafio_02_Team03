package com.team03.challenge02.teacher.service;

import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.security.JwtTokenService;
import com.team03.challenge02.security.SecurityConfiguration;
import com.team03.challenge02.security.UserDetailsImpl;
import com.team03.challenge02.teacher.dto.LoginRequest;
import com.team03.challenge02.teacher.dto.RecoveryJwtTokenDTO;
import com.team03.challenge02.teacher.entity.Teacher;
import com.team03.challenge02.teacher.exceptions.DuplicatedDisciplinesException;
import com.team03.challenge02.teacher.exceptions.InvalidCouseSubjectException;
import com.team03.challenge02.teacher.exceptions.TooManySubjectsException;
import com.team03.challenge02.teacher.repository.TeacherRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private final SecurityConfiguration securityConfiguration;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public Teacher create(Teacher teacher) {
        System.out.println("Password before encoding: " + teacher.getPassword());
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return teacherRepository.save(teacher);
    }

    @Transactional(readOnly = true)
    public Teacher getById(long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Transactional
    public void delete(long id) {
        if (!teacherRepository.existsById(id)) {
            throw new IllegalArgumentException("Teacher not found with id: " + id);
        }
        teacherRepository.deleteById(id);
    }
    @Transactional
    public void changePassword(String email, String newPassword) {
        Teacher teacher = teacherRepository.findByEmail(email);
        if (teacher != null) {
            teacher.setPassword(passwordEncoder.encode(newPassword));
            teacherRepository.save(teacher);
        } else {
            throw new IllegalArgumentException("Teacher not found");
        }
    }

    private void validateDiscipline(Teacher teacher) {
        List<Discipline> holdingSubjects = teacher.getHoldingSubjects();
        List<Discipline> substituteSubjects = teacher.getSubstituteSubjects();

        if (holdingSubjects.size() > 2) {
            throw new TooManySubjectsException("Too many holding subjects");
        }

        if(substituteSubjects.size() > 2) {
            throw new TooManySubjectsException("Too many substitute subjects");
        }

        Set<Discipline> allDisciplines = new HashSet<>(holdingSubjects);
        allDisciplines.addAll(substituteSubjects);

        if(allDisciplines.size() < (holdingSubjects.size() + substituteSubjects.size())) {
            throw new DuplicatedDisciplinesException("Duplicate disciplines are not allowed");
        }

        for(Discipline discipline : holdingSubjects) {
            if(!discipline.getFullTeacher().getCourse().equals(teacher.getCourse())) {
                throw new InvalidCouseSubjectException("Holding subjects must be from teacher's course");
            }
        }

        for(Discipline discipline : substituteSubjects) {
            if(substituteSubjects.indexOf(discipline) < 2 && !discipline.getFullTeacher().getCourse().equals(teacher.getCourse())) {
                throw new InvalidCouseSubjectException("Substitute subjects must be from teacher's course");
            }
        }
    }

    public RecoveryJwtTokenDTO authenticateUser(@Valid LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDTO(jwtTokenService.generateToken(userDetails));
    }
}
