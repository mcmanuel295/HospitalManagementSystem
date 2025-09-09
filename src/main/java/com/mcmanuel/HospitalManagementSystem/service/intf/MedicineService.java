package com.mcmanuel.HospitalManagementSystem.service.intf;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;


public interface MedicineService {

    Medicine addMedicine(Medicine medicine);
    Medicine getMedicine(String medicineId);
    Medicine updateMedicine(String medicineId,Medicine updatedMedicine);
    void deleteMedicine(String medicineId);
}
