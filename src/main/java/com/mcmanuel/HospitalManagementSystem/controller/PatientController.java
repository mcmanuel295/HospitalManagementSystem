package com.mcmanuel.HospitalManagementSystem.controller;

import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.service.intf.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    ResponseEntity<Patient> registserPatient(@RequestBody Patient patient){
        return new ResponseEntity<>(patientService.registerPatient(patient),HttpStatus.CREATED);
    }

    @PostMapping
    ResponseEntity<Patient> createPateint(@RequestBody Patient patient){
        return new ResponseEntity<>(patientService.createPatient(patient),HttpStatus.CREATED);
    }


    @PostMapping("/")
    ResponseEntity<Patient> getUserById(String userId) throws NoSuchElementException{
        patientService.getUserById(userId);
    }

    Patient getUserByEmail(String email) throws NoSuchElementException;

    List<Patient> getAllUser();

    Patient updateUser(String userUd, Patient updatedUser) throws NoSuchElementException;

    void deleteUser(String userId) throws NoSuchElementException
}
