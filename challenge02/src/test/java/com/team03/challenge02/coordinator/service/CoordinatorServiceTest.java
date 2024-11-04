package com.team03.challenge02.coordinator.service;

import com.team03.challenge02.coordinator.entity.Coordinator;
import com.team03.challenge02.coordinator.repository.CoordinatorRepository;
import com.team03.challenge02.exception.EntityIdNotFoundException;
import com.team03.challenge02.security.JwtTokenService;
import com.team03.challenge02.security.UserDetailsImpl;
import com.team03.challenge02.teacher.dto.LoginRequest;
import com.team03.challenge02.teacher.dto.RecoveryJwtTokenDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CoordinatorServiceTest {

    @Mock
    private CoordinatorRepository repository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenService jwtTokenService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CoordinatorService coordinatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldReturnCoordinator() {
        Coordinator coordinator = new Coordinator();
        coordinator.setPassword("password");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(repository.save(any(Coordinator.class))).thenReturn(coordinator);

        Coordinator result = coordinatorService.create(coordinator);

        assertNotNull(result);
        assertEquals("encodedPassword", result.getPassword());
        verify(repository).save(coordinator);
    }

    @Test
    void update_ShouldReturnUpdatedCoordinator() {
        Long id = 1L;
        Coordinator existingCoordinator = new Coordinator();
        existingCoordinator.setId(id);
        existingCoordinator.setFirstName("John");
        existingCoordinator.setLastName("Doe");

        Coordinator updatedCoordinator = new Coordinator();
        updatedCoordinator.setFirstName("Jane");
        updatedCoordinator.setLastName("Smith");

        when(repository.findById(id)).thenReturn(Optional.of(existingCoordinator));
        when(repository.save(existingCoordinator)).thenReturn(existingCoordinator);

        Coordinator result = coordinatorService.update(id, updatedCoordinator);

        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        verify(repository).save(existingCoordinator);
    }

    @Test
    void findById_ShouldThrowEntityIdNotFoundException() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityIdNotFoundException.class, () -> coordinatorService.findById(id));
    }

    @Test
    void authenticateUser_ShouldReturnRecoveryJwtTokenDTO() {
        LoginRequest loginRequest = new LoginRequest("email@example.com", "password");
        Authentication authentication = mock(Authentication.class);
        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtTokenService.generateToken(userDetails)).thenReturn("jwtToken");

        RecoveryJwtTokenDTO result = coordinatorService.authenticateUser(loginRequest);

        assertNotNull(result);
        assertEquals("jwtToken", result.token());
    }
}
