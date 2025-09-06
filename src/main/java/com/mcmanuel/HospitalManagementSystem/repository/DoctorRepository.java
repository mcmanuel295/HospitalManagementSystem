package com.mcmanuel.HospitalManagementSystem.repository;

import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    @Query(value = "SELECT d FROM Doctor d WHERE d.available =true && d.specialization = :specialty",nativeQuery = true)
    Doctor findAvailableDoctors(String specialty);
}
