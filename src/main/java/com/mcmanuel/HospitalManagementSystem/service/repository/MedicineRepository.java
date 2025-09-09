package com.mcmanuel.HospitalManagementSystem.service.repository;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository  extends JpaRepository<Medicine,String> {
}
