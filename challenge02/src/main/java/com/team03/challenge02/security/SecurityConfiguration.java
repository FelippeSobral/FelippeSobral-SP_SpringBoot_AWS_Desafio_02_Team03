package com.team03.challenge02.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserAuthenticationFilter userAuthenticationFilter;

    public static final String[] POST_ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/api/teacher/login",
            "/api/student/login",
            "/api/student/two",
            "/api/coordinator/login"
    };

    public static final String[] GET_ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/api/course/id/*",
            "/api/course/name/*",
            "/api/course",
            "/api/disciplines",
            "/api/disciplines/*"
    };

    public static final String[] GET_ENDPOINTS_TEACHER = {
            "api/teacher",
            "api/teacher/*",
            "api/teacher",
            "api/teacher/delete",
            "api/student",
            "api/student/*"

    };

    public static final String[] POST_ENDPOINTS_TEACHER = {
            "api/teacher",
    };

    public static final String[] ENDPOINTS_STUDENTS = {
            "api/student"
    };

    public static final String[] POST_ENDPOINTS_COORDINATOR = {
            "/api/coordinator",
            "/api/course"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, POST_ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(HttpMethod.GET, GET_ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(HttpMethod.GET, GET_ENDPOINTS_TEACHER).hasRole("TEACHER")
                        .requestMatchers(HttpMethod.POST, POST_ENDPOINTS_TEACHER).hasRole("TEACHER")
                        .requestMatchers(HttpMethod.GET, ENDPOINTS_STUDENTS).hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST, POST_ENDPOINTS_COORDINATOR).hasRole("COORDINATOR")
                        .anyRequest().denyAll())
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
