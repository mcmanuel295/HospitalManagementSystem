package com.mcmanuel.HospitalManagementSystem.repository;

import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,String> {
}
