package com.team03.challenge02.security;

import com.team03.challenge02.coordinator.entity.Coordinator;
import com.team03.challenge02.coordinator.repository.CoordinatorRepository;
import com.team03.challenge02.student.entity.Student;
import com.team03.challenge02.student.repository.StudentRepository;
import com.team03.challenge02.teacher.entity.Teacher;
import com.team03.challenge02.teacher.repository.TeacherRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final CoordinatorRepository coordinatorRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Verifica se a requisição precisa de autenticação
        if (checkIfEndpointIsNotPublic(request)) {
            // Recupera o token do cabeçalho Authorization
            String token = recoveryToken(request);
            if (token != null) {
                // Obtém o "subject" do token, que é usado aqui como identificador do usuário (email)
                String subject = jwtTokenService.getSubjectFromToken(token);

                Authentication authentication = null;

                // Tenta localizar o usuário em diferentes repositórios
                // Primeiro, tenta localizar o Teacher
                Teacher teacher = teacherRepository.findByEmail(subject);
                if (teacher != null) {
                    // Cria detalhes do usuário e objeto de autenticação para Teacher
                    UserDetailsImpl userDetails = new UserDetailsImpl(teacher);
                    authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                } else {
                    // Se não encontrado, tenta localizar o Student
                    Student student = studentRepository.findByEmail(subject);
                    if (student != null) {
                        // Cria detalhes do usuário e objeto de autenticação para Student
                        UserDetailsImpl userDetails = new UserDetailsImpl(student);
                        authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    } else {
                        // Se ainda não encontrado, tenta localizar o Coordinator
                        Coordinator coordinator = coordinatorRepository.findByEmail(subject);
                        if (coordinator != null) {
                            // Cria detalhes do usuário e objeto de autenticação para Coordinator
                            UserDetailsImpl userDetails = new UserDetailsImpl(coordinator);
                            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        }
                    }
                }

                // Configura o contexto de segurança do Spring se a autenticação foi bem sucedida
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    // Lança exceção se o usuário não foi encontrado em nenhum repositório
                    throw new RuntimeException("O usuário não foi encontrado.");
                }
            } else {
                // Lança exceção se o token estiver ausente
                throw new RuntimeException("O token está ausente.");
            }
        }
        // Continua a cadeia de processamento da requisição
        filterChain.doFilter(request, response);
    }

    // Método auxiliar para recuperar o token JWT do cabeçalho Authorization
    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            // Remove o prefixo "Bearer " para obter o token
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    // Verifica se a requisição é para um endpoint que requer autenticação
    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        // Compara a URI da requisição com endpoints públicos definidos
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
    }
}


/*
package com.team03.challenge02.security;

import com.team03.challenge02.teacher.entity.Teacher;
import com.team03.challenge02.teacher.repository.TeacherRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final TeacherRepository teacherRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Verifica se o endpoint requer autenticação antes de processar a requisição
        if (checkIfEndpointIsNotPublic(request)) {
            String token = recoveryToken(request); // Recupera o token do cabeçalho Authorization da requisição
            if (token != null) {
                String subject = jwtTokenService.getSubjectFromToken(token); // Obtém o assunto (neste caso, o nome de usuário) do token
                Teacher teacher = teacherRepository.findByEmail(subject); // Busca o usuário pelo email (que é o assunto do token)
                UserDetailsImpl userDetails = new UserDetailsImpl(teacher); // Cria um UserDetails com o usuário encontrado

                // Cria um objeto de autenticação do Spring Security
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                // Define o objeto de autenticação no contexto de segurança do Spring Security
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new RuntimeException("O token está ausente.");
            }
        }
        filterChain.doFilter(request, response); // Continua o processamento da requisição
    }



    // Recupera o token do cabeçalho Authorization da requisição
    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    // Verifica se o endpoint requer autenticação antes de processar a requisição
    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
    }

}
*/
