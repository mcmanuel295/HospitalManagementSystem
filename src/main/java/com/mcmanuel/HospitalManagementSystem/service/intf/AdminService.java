package com.mcmanuel.HospitalManagementSystem.service.intf;


import com.mcmanuel.HospitalManagementSystem.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.NoSuchElementException;

public interface AdminService {

    User getWorkerById(String userId) throws UsernameNotFoundException;

    List<User> getAllUser();

    String addAdmin(String adminId);

    String addAdminByEmail(String email);

    String removeAdmin(String adminId);

//    Admin updateUser(String userId, User updatedUser) throws NoSuchElementException;

    void deleteUser(String userId) throws NoSuchElementException;
}
