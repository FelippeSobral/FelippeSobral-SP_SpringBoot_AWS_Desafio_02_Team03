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

    public static final String[] COURSE_ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/api/course",
            "/api/course/*"
    };

    public static final String[] ENDPOINTS_COURSE = {
            "/api/course",
            "/api/course/*"
    };

    public static final String[] TEACHER_ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/api/teacher/login"
    };

    public static final String[] ENDPOINTS_TEACHER = {
            "/api/teacher"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, TEACHER_ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(HttpMethod.GET, COURSE_ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(HttpMethod.POST, ENDPOINTS_COURSE).hasRole("COORDINATOR")
                        .requestMatchers(HttpMethod.DELETE, ENDPOINTS_COURSE).hasRole("COORDINATOR")
                        .requestMatchers(HttpMethod.PUT, ENDPOINTS_COURSE).hasRole("COORDINATOR")
                        .requestMatchers(HttpMethod.GET, ENDPOINTS_TEACHER).hasRole("TEACHER")
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
