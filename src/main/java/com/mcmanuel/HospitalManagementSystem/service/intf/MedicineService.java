package com.mcmanuel.HospitalManagementSystem.service.intf;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;

import java.util.UUID;

public interface MedicineService {

    Medicine addMedicine(Medicine medicine);
    Medicine getMedicine(UUID medicneId);
    Medicine updateMedicine(Medicine updatedMedicine);
    void deleteMedicine(UUID medicneId);
}
