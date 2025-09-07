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

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;


    @PostMapping("/")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> addDoctor(@RequestBody AddDoctorRequest request){
        System.out.println("in the add Doctor method");
        var doctor = doctorService.addDoctor(request);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.CREATED);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Integer doctorId) throws NoSuchElementException {
        var doctor = doctorService.getUserById(doctorId);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

    @GetMapping("/available/{specialty}")
    public ResponseEntity<List<Doctor>> getAvailableDoctors(@PathVariable String specialty) throws NoSuchElementException {
        var doctor = doctorService.getAvailableDoctors(specialty);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

    @GetMapping("/")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        var doctor = doctorService.getAllUser();
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

    @PutMapping("/{doctorId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Integer doctorId,@RequestBody Doctor updatedDoctor) throws NoSuchElementException {
        var doctor = doctorService.updateUser(doctorId,updatedDoctor);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }


    @DeleteMapping("/{doctorId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer doctorId) throws NoSuchElementException {
        doctorService.deleteUser(doctorId);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }


    @GetMapping("/{doctorId}/patients")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Patient>> getAssignedPatients(@PathVariable Integer doctorId) throws Exception {
        var doctor = doctorService.assignedPatients(doctorId);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

}
