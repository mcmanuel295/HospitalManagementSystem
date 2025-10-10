package com.mcmanuel.HospitalManagementSystem.controller;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;
import com.mcmanuel.HospitalManagementSystem.service.intf.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.LimitExceededException;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicines")
public class MedicineController {
    private final MedicineService medicineService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_PHARMACIST') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Medicine> addMedicine(@RequestBody @Validated Medicine medicine){
        return new ResponseEntity<>(medicineService.addMedicine(medicine), HttpStatus.CREATED);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_PHARMACIST') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Medicine> getMedicine(@PathVariable String medicineId){
        Medicine medicine = medicineService.getMedicine(medicineId);
        if (medicine == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(medicine,HttpStatus.OK);
    }

    @PutMapping("/medicineId")
    @PreAuthorize("hasRole('ROLE_PHARMACIST') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable String medicineId,@RequestBody Medicine updatedMedicine){
        Medicine medicine = medicineService.updateMedicine(medicineId,updatedMedicine);
        if (medicine == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(medicine,HttpStatus.OK);
    }

    @DeleteMapping("/medicineId")
    @PreAuthorize("hasRole('ROLE_PHARMACIST') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteMedicine(@PathVariable String medicineId){
        medicineService.deleteMedicine(medicineId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{medicineId}/name")
    @PreAuthorize("hasRole('ROLE_PHARMACIST') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> getMedicineName(@PathVariable String medicineId){
        String name = medicineService.getMedicineName(medicineId);
        if (name == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(name,HttpStatus.OK);
    }


    @GetMapping("/{medicineId}/remove")
    @PreAuthorize("hasRole('ROLE_PHARMACIST') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> removeMedicine(@PathVariable String medicineId) throws LimitExceededException {
        Medicine medicine = medicineService.removeMedicine(medicineId);
        if (medicine == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Medicine removed",HttpStatus.OK);

    }


    @GetMapping("/{medicineId}/distributor")
    @PreAuthorize("hasRole('ROLE_PHARMACIST') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Set<String>> getDistributorName(String medicineId){
        Set<String> medicine = medicineService.getDistributor(medicineId);
        if (medicine.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(medicine,HttpStatus.OK);

    }

    @GetMapping("/distributors")
    @PreAuthorize("hasRole('ROLE_PHARMACIST') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Set<String>> getDistributorAllName(){
        Set<String> medicine = medicineService.getDistributorAll();
        if (medicine.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(medicine,HttpStatus.OK);
    }

}
