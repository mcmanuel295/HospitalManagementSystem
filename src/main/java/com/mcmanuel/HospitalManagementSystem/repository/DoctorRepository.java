package com.mcmanuel.HospitalManagementSystem.repository;

import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

    @Query(value = "SELECT d FROM Doctor d WHERE d.available = true AND :specialty MEMBER OF d.specialization")
    List<Doctor> findAvailableDoctors(@Param("specialty") String specialty);

    Doctor findByEmail(String email);
}
