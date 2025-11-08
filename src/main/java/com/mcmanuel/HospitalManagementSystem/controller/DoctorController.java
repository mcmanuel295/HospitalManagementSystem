package com.mcmanuel.HospitalManagementSystem.controller;

import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.service.intf.DoctorService;
import com.mcmanuel.HospitalManagementSystem.request.DoctorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;


    @PostMapping("/")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> addDoctor(@RequestBody DoctorRequest request){
        var doctor = doctorService.addDoctor(request);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.CREATED);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable String doctorId) throws NoSuchElementException {
        var doctor = doctorService.getUserById(doctorId);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

    @GetMapping("/{email}/email")
    public ResponseEntity<Doctor> getDoctorByEmail(@PathVariable String email) throws NoSuchElementException {
        var doctor = doctorService.getUserByEmail(email);
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
    public ResponseEntity<Doctor> updateDoctor(@PathVariable String doctorId,@RequestBody Doctor updatedDoctor) throws NoSuchElementException {
        var doctor = doctorService.updateUser(doctorId,updatedDoctor);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }


    @DeleteMapping("/{doctorId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteDoctor(@PathVariable String doctorId) throws NoSuchElementException {
        doctorService.deleteUser(doctorId);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }


    @GetMapping("/{doctorId}/patients")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Patient>> getAssignedPatients(@PathVariable String doctorId) throws Exception {
        var doctor = doctorService.getAssignedPatients(doctorId);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

    @PutMapping("/{doctorId}/update-Availability/")
    public ResponseEntity<String> updateAvailability(@PathVariable String doctorId) throws Exception {
        var availability = doctorService.updateAvailability(doctorId);
        if (availability == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(availability,HttpStatus.OK);
    }

//    @PutMapping("/{doctorId}/unassign/{patientId}")
//    public ResponseEntity<String> unassignPateint(@PathVariable @Validated String doctorId,@PathVariable @Validated String patientId){
//        String assign =doctorService.unassignPatient(doctorId,patientId);
//        if (assign == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(assign,HttpStatus.OK);
//    }

    @GetMapping("/allSpecialization")
    public ResponseEntity<Set<String>> getAllSpecialty(){
        return new ResponseEntity<>(doctorService.getAllSpecialty(),HttpStatus.OK);
    }
}
