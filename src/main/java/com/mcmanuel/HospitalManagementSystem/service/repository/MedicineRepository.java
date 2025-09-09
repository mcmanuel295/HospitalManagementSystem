package com.mcmanuel.HospitalManagementSystem.service.repository;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicineRepository  extends JpaRepository<Medicine,String> {

    Optional<Medicine> findByMedicineName(String medicineName);
}
