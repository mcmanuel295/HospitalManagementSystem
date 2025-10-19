package com.mcmanuel.HospitalManagementSystem.service.intf;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;

import javax.naming.LimitExceededException;
import java.util.List;
import java.util.Set;


public interface MedicineService {

    Medicine addMedicine(Medicine medicine);

    Medicine getMedicine(String medicineId);

    List<Medicine> getAllMedicines();

    Medicine updateMedicine(String medicineId,Medicine updatedMedicine);

    void deleteMedicine(String medicineId);

    String getMedicineName(String medicineId);

    Medicine removeMedicine(String medicineId) throws LimitExceededException;

    Set<String> getDistributor(String medicineId);

    Set<String> getDistributorAll();

}
