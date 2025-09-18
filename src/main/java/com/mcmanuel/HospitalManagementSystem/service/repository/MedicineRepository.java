package com.mcmanuel.HospitalManagementSystem.service.repository;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface MedicineRepository  extends JpaRepository<Medicine,String> {

    Optional<Medicine> findByMedicineName(String medicineName);

    @Query(value = "SELECT d FROM Medicine d WHERE d.distributor :medicineId")
    Optional<Set<String>> findDistributors(String medicineId);
}
