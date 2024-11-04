package com.team03.challenge02.coordinator.service;

import com.team03.challenge02.coordinator.dto.PassworDTO;
import com.team03.challenge02.coordinator.entity.Coordinator;
import com.team03.challenge02.coordinator.repository.CoordinatorRepository;
import com.team03.challenge02.exception.EntityIdNotFoundException;
import com.team03.challenge02.exception.NameUniqueViolationException;
import com.team03.challenge02.security.JwtTokenService;
import com.team03.challenge02.security.UserDetailsImpl;
import com.team03.challenge02.teacher.dto.LoginRequest;
import com.team03.challenge02.teacher.dto.RecoveryJwtTokenDTO;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatorService {

    private final CoordinatorRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    public CoordinatorService(CoordinatorRepository repository, AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public Coordinator create(Coordinator cr){
        try {
            cr.setPassword(passwordEncoder.encode(cr.getPassword()));
            return repository.save(cr);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw  new NameUniqueViolationException(String.format("Information exists in our data base"));
        }
    }

    public Coordinator update(Long id, Coordinator coordinator){
        Coordinator cr = findById(id);
        try {
            cr.setFirstName(coordinator.getFirstName());
            cr.setLastName(coordinator.getLastName());
            cr.setCourse(coordinator.getCourse());
            cr.setEmail(coordinator.getEmail());
            cr.setDisciplinas(coordinator.getDisciplinas());
            cr.setBirthDate(coordinator.getBirthDate());
        } catch (org.springframework.transaction.TransactionSystemException ex) {
            throw  new NameUniqueViolationException(String.format("Fill in the fields"));
        }
        return repository.save(cr);
    }

    public Coordinator findByEmail(String email){
        return repository.findByEmail(email);
    }

    public Coordinator updatePassword(String email, PassworDTO passworDTO){
        Coordinator cr = findByEmail(email);
        if (!cr.getPassword().equals(passworDTO.getCurrentPassword())){
            throw new RuntimeException("Incorrect password");
        }
        if (!passworDTO.getNewPassword().equals(passworDTO.getRepetePassword())){
            throw new RuntimeException("Different password");
        }
        cr.setPassword(passworDTO.getNewPassword());
        return repository.save(cr);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Coordinator findById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(String.format("Information of id {%s} not found", id)));
    }

    public List<Coordinator> findAll(){
        return repository.findAll();
    }

    public RecoveryJwtTokenDTO authenticateUser(@Valid LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDTO(jwtTokenService.generateToken(userDetails));
    }


}
