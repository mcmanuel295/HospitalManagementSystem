package com.mcmanuel.HospitalManagementSystem.controller;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;
import com.mcmanuel.HospitalManagementSystem.service.intf.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicines")
public class MedicineController {
    private final MedicineService medicineService;

    @PostMapping("/")
    public ResponseEntity<Medicine> addMedicine(@RequestBody @Validated Medicine medicine){
        return new ResponseEntity<>(medicineService.addMedicine(medicine), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Medicine> getMedicine(@PathVariable String medicineId){
        Medicine medicine = medicineService.getMedicine(medicineId);
        if (medicine == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(medicine,HttpStatus.OK);
    }

    @PutMapping("/medicineId")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable String medicineId,@RequestBody Medicine updatedMedicine){
        Medicine medicine = medicineService.updateMedicine(medicineId,updatedMedicine);
        if (medicine == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(medicine,HttpStatus.OK);
    }

    @DeleteMapping("/medicineId")
    public ResponseEntity<String> deleteMedicine(@PathVariable String medicineId){
        medicineService.deleteMedicine(medicineId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
