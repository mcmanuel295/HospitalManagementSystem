package com.mcmanuel.HospitalManagementSystem.repository;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface MedicineRepository  extends JpaRepository<Medicine,String> {

    Optional<Medicine> findByMedicineName(String medicineName);

    @Query(value = "SELECT d FROM Medicine d WHERE :medicineId MEMBER OF d.distributor")
    Optional<Set<String>> findByDistributors(String medicineId);

    @Query(value = "SELECT d FROM Medicine d")
    Set<String> findAllDistributors();

}
