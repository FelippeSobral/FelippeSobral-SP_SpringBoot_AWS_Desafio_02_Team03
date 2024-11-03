package com.team03.challenge02.security;

import com.team03.challenge02.coordinator.entity.Coordinator;
import com.team03.challenge02.coordinator.repository.CoordinatorRepository;
import com.team03.challenge02.student.entity.Student;
import com.team03.challenge02.student.repository.StudentRepository;
import com.team03.challenge02.teacher.entity.Teacher;
import com.team03.challenge02.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetaisServiceImpl implements UserDetailsService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final CoordinatorRepository coordinatorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tenta encontrar um Professor
        Teacher teacher = teacherRepository.findByEmail(username);
        if (teacher != null) {
            return new UserDetailsImpl(teacher);
        }

        // Tenta encontrar um Estudante
        Student student = studentRepository.findByEmail(username);
        if (student != null) {
            return new UserDetailsImpl(student);
        }

        // Tenta encontrar um Coordenador
        Coordinator coordinator = coordinatorRepository.findByEmail(username);
        if (coordinator != null) {
            return new UserDetailsImpl(coordinator);
        }

        // Se nenhum usuário for encontrado, lança exceção
        throw new UsernameNotFoundException("Usuário não encontrado com e-mail: " + username);
    }
}
