package com.mcmanuel.HospitalManagementSystem.repository;

import com.mcmanuel.HospitalManagementSystem.entity.Admin;
import com.mcmanuel.HospitalManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,String> {


    @Query(value = "SELECT email FROM user where email =:email",nativeQuery = true)
    Optional<String> findByEmail(String email);


    @Query(value = "SELECT * FROM user ", nativeQuery = true)
    List<User> getAll();

    Optional<String> findByDepartment(String userId);
}
