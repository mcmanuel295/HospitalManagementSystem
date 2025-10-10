package com.mcmanuel.HospitalManagementSystem.repository;

import com.mcmanuel.HospitalManagementSystem.entity.Admin;
import com.mcmanuel.HospitalManagementSystem.entity.User;
import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,String> {


    @Query(value = "SELECT roles FROM doctor WHERE user_id = :userId "+
            "UNION SELECT roles FROM pharmacist WHERE user_id = :userId " +
            "UNION SELECT roles FROM receptionist WHERE user_id = :userId",nativeQuery = true)

    List<String> findByUserId(@Param("userId") String userId);


    @Query(value = "SELECT * FROM doctor UNION SELECT * FROM pharmacist UNION SELECT * FROM receptionist ", nativeQuery = true)
    List<User> getAll();
}
