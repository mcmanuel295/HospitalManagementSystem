package com.mcmanuel.HospitalManagementSystem.repository;

import com.mcmanuel.HospitalManagementSystem.entity.Admin;
import com.mcmanuel.HospitalManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,String> {

    @Query(value = "SELECT * FROM user ", nativeQuery = true)
    List<User> getAll();


    @Query(value = "SELECT d.department FROM User d where d.userId = :userId")
    Optional<String> getUserDepartment(String userId);

    @Query(value = "SELECT d.department FROM User d where d.email = :email")
    Optional<String> getUserDepartmentByEmail(String email);


}
