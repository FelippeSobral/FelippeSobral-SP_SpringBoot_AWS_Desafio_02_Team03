package com.team03.challenge02.registration.controller;

import com.team03.challenge02.registration.Services.RegistrationServices;
import com.team03.challenge02.registration.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Registration")
public class RegistrationController {

@Autowired
private RegistrationServices registrationServices;

@PostMapping
@PreAuthorize("hasRole('COORDINATOR')")
public ResponseEntity<RegistrationDTO> createRegistration(@RequestBody RegistrationDTO registrationDTO) {
    RegistrationDTO newRegistration = registrationServices.createRegistration(registrationDTO.student_id(), registrationDTO.course_id());
    return ResponseEntity.status(HttpStatus.CREATED).body(newRegistration);
}

@GetMapping("studant/{id}")
public ResponseEntity<List<RegistrationDTO>> listRegistrations(@PathVariable Long student_id) {
    List<RegistrationDTO> registrations = registrationServices.listRegistrationsByStudent(student_id);
    return ResponseEntity.ok(registrations);
}

}
