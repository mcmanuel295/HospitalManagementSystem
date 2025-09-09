package com.mcmanuel.HospitalManagementSystem.service.intf;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;
import java.util.Set;


public interface MedicineService {

    Medicine addMedicine(Medicine medicine);
    Medicine getMedicine(String medicineId);
    Medicine updateMedicine(String medicineId,Medicine updatedMedicine);
    void deleteMedicine(String medicineId);
    String getMedicineName(String medicineId);
    Medicine removeMedicine(String medicineId);
    Set<String> getDistributorName(String medicineId);
    Set<String> getDistributorAllName();
}
