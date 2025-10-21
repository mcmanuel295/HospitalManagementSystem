package com.mcmanuel.HospitalManagementSystem.controller;

import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.service.intf.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/")
    ResponseEntity<Patient> registserPatient(@RequestBody @Validated Patient patient){
        return new ResponseEntity<>(patientService.registerPatient(patient),HttpStatus.CREATED);
    }


    @GetMapping("/{userId}")
    ResponseEntity<Patient> getUserById(@PathVariable @Validated String userId) throws NoSuchElementException{
        Patient patient = patientService.getUserById(userId);
        if (patient==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }


    @GetMapping("/email/{email}")
    ResponseEntity<Patient> getUserByEmail(@PathVariable @Validated String email) throws NoSuchElementException{
        Patient patient = patientService.getUserByEmail(email);
        if (patient==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }

    @GetMapping("/")
    ResponseEntity<List<Patient>> getAllUser(){
        return new ResponseEntity<>(patientService.getAllUser(),HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    ResponseEntity<Patient> updateUser(@PathVariable String userId, @RequestBody Patient updatedUser) throws NoSuchElementException{
        Patient patient = patientService.updateUser(userId,updatedUser);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }

    ResponseEntity<String> deleteUser(String userId) throws NoSuchElementException{
        patientService.deleteUser(userId);
        return new ResponseEntity<>("Delelted",HttpStatus.OK);
    }

//    @PostMapping
//    ResponseEntity<Patient> assignPateint(@RequestBody String patientId){
//        return new ResponseEntity<>(patientService.assignPatient(patientId),HttpStatus.CREATED);
//    }

    @GetMapping("/ask-ai/{prompt}")
    ResponseEntity<Patient> assignPateint(@PathVariable String prompt){
        return new ResponseEntity<>(patientService.assignPatient(prompt),HttpStatus.OK);
    }
}
