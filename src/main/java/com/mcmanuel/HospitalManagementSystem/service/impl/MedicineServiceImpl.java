package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;
import com.mcmanuel.HospitalManagementSystem.service.intf.MedicineService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MedicineServiceImpl implements MedicineService {
    @Override
    public Medicine addMedicine(Medicine medicine) {
        return null;
    }

    @Override
    public Medicine getMedicine(UUID medicneId) {
        return null;
    }

    @Override
    public Medicine updateMedicine(Medicine updatedMedicine) {
        return null;
    }

    @Override
    public void deleteMedicine(UUID medicneId) {

    }
}
