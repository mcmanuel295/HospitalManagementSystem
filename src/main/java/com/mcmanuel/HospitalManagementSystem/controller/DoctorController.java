package com.mcmanuel.HospitalManagementSystem.controller;

import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.service.intf.DoctorService;
import com.mcmanuel.HospitalManagementSystem.service.request.AddDoctorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;


    @PostMapping("/")
    @PreAuthorize("hasAutohrity")
    public ResponseEntity<Doctor> addDoctor(@RequestBody AddDoctorRequest request){
        var doctor = doctorService.addDoctor(request);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.CREATED);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable UUID doctorId) throws NoSuchElementException {
        var doctor = doctorService.getUserById(doctorId);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

    @GetMapping("/{specialty}")
    public ResponseEntity<Doctor> getAvailableDoctors(@PathVariable String specialty) throws NoSuchElementException {
        var doctor = doctorService.getAvailableDoctors(specialty);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        var doctor = doctorService.getAllUser();
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable UUID doctorId,@RequestBody Doctor updatedDoctor) throws NoSuchElementException {
        var doctor = doctorService.updateUser(doctorId,updatedDoctor);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }


    @DeleteMapping("/{doctorId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable UUID doctorId) throws NoSuchElementException {
        doctorService.deleteUser(doctorId);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }


    @GetMapping("/{doctorId}/patients")
    public ResponseEntity<List<Patient>> getAssignedPatients(@PathVariable UUID doctorId) throws Exception {
        var doctor = doctorService.assignedPatients(doctorId);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

}
