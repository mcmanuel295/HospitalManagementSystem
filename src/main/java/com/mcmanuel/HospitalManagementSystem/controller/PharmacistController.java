package com.mcmanuel.HospitalManagementSystem.controller;

import com.mcmanuel.HospitalManagementSystem.entity.Pharmacist;
import com.mcmanuel.HospitalManagementSystem.request.PharmacistRequest;
import com.mcmanuel.HospitalManagementSystem.service.intf.PharmacistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacists")
public class PharmacistController {
    private final PharmacistService pharmacistService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Pharmacist> addPharmacist(@RequestBody @Validated PharmacistRequest request){
        var pharmacist = pharmacistService.addPharmacist(request);
        if (pharmacist == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pharmacist,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{pharmacistId}")
    public ResponseEntity<Pharmacist> getPharmacistById(@PathVariable String pharmacistId) throws NoSuchElementException {
        var pharmacist = pharmacistService.getUserById(pharmacistId);
        if (pharmacist == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pharmacist,HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{email}/email")
    public ResponseEntity<Pharmacist> getPharmacistByEmail(@PathVariable String email) throws NoSuchElementException {
        var doctor = pharmacistService.getUserByEmail(email);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<Pharmacist>> getAllPharmacists() {
        var pharmacists = pharmacistService.getAllUser();
        if (pharmacists == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pharmacists,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{pharmacistId}")
    public ResponseEntity<Pharmacist> updateDoctor(@PathVariable String pharmacistId,@RequestBody Pharmacist updatedPharmacist) throws NoSuchElementException {
        var pharmacist = pharmacistService.updateUser(pharmacistId,updatedPharmacist);
        if (pharmacist == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pharmacist,HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{pharmacistId}")
    public ResponseEntity<String> deletePharmacist(@PathVariable String pharmacistId) throws NoSuchElementException {
        pharmacistService.deleteUser(pharmacistId);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

}
