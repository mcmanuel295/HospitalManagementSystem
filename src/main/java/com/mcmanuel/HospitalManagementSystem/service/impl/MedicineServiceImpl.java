package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;
import com.mcmanuel.HospitalManagementSystem.service.intf.MedicineService;
import com.mcmanuel.HospitalManagementSystem.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.naming.LimitExceededException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepo;

    @Override
    public Medicine addMedicine(@Validated Medicine medicine) {
        Optional<Medicine> savedMedicine = medicineRepo.findByMedicineName(medicine.getMedicineName());
        if (savedMedicine.isEmpty()) {
           return medicineRepo.save(medicine);
        }

        savedMedicine.get().setQuantity( medicine.getQuantity()+1);
        savedMedicine.get().getDistributor().addAll(medicine.getDistributor());

        return medicineRepo.save(savedMedicine.get());
    }


    @Override
    public Medicine getMedicine(String medicineId)throws NoSuchElementException {
        return medicineRepo.findById(medicineId).orElseThrow();
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepo.findAll();
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

    @Override
    public String getMedicineName(String medicineId) {
        return medicineRepo.findById(medicineId)
                .map(Medicine::getMedicineName)
                .orElseThrow();
    }

    @Override
    public Medicine removeMedicine(String medicineId) throws LimitExceededException,NoSuchElementException {
        Medicine medicine = medicineRepo.findById(medicineId).orElseThrow();
        if(medicine.getQuantity()==0){
            throw new LimitExceededException("No quantity available");
        }
        medicine.setQuantity( medicine.getQuantity()-1);
        return medicineRepo.save(medicine);
    }


    @Override
    public Set<String> getDistributor(String medicineId) {
        Medicine medicine = medicineRepo.findById(medicineId).orElseThrow();
        return medicine.getDistributor();
    }

    @Override
    public Set<String> getDistributorAll() {
        return medicineRepo.findAllDistributors();
    }
}
