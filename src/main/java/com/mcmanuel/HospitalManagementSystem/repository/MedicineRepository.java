package com.mcmanuel.HospitalManagementSystem.repository;

import com.mcmanuel.HospitalManagementSystem.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface MedicineRepository  extends JpaRepository<Medicine,String> {

    Optional<Medicine> findByMedicineName(String medicineName);

    @Query(value = "SELECT m.distributor FROM Medicine m")
    Set<String> findAllDistributors();

}
