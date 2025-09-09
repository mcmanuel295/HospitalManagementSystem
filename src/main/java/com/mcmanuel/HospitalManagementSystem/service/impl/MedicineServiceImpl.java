package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;
import com.mcmanuel.HospitalManagementSystem.service.intf.MedicineService;
import com.mcmanuel.HospitalManagementSystem.service.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepo;

    @Override
    public Medicine addMedicine(@Validated Medicine medicine) {
        return medicineRepo.save(medicine);
    }

    @Override
    public Medicine getMedicine(String medicineId)throws NoSuchElementException {
        return medicineRepo.findById(medicineId).orElseThrow();
    }

    @Override
    public Medicine updateMedicine(String medicineId, Medicine updatedMedicine) throws NoSuchElementException{
        Medicine medicine =  medicineRepo.findById(medicineId).orElseThrow();

        updatedMedicine.setMedicineId(medicine.getMedicineId());
        return medicineRepo.save(updatedMedicine);
    }

    @Override
    public void deleteMedicine(String medicineId)throws NoSuchElementException {
        Medicine medicine =  medicineRepo.findById(medicineId).orElseThrow();
        medicineRepo.delete(medicine);
    }
}
