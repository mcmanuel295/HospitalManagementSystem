package com.mcmanuel.HospitalManagementSystem.service.repository;

import com.mcmanuel.HospitalManagementSystem.entity.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PharmacistRepository extends JpaRepository<Pharmacist, UUID> {
}
