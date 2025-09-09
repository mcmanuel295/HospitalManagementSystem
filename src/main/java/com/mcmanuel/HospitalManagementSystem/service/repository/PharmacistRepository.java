package com.mcmanuel.HospitalManagementSystem.service.repository;

import com.mcmanuel.HospitalManagementSystem.entity.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PharmacistRepository extends JpaRepository<Pharmacist, String> {
}
