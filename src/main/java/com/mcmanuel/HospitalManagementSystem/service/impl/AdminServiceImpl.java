package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.*;
import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import com.mcmanuel.HospitalManagementSystem.repository.AdminRepository;
import com.mcmanuel.HospitalManagementSystem.service.intf.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepo;
    private final DoctorService doctorService;
    private final PharmacistService pharmacistService;
    private final ReceptionistService receptionistService;


    @Override
    public String addAdmin(String adminId) throws UsernameNotFoundException {
        List<String> user = adminRepo.findByUserId(adminId);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid user");
        }
        System.out.println("String returned from the database is "+user);

        String name = user.stream().filter(role -> !role.equals(Role.ADMIN.name())).toString();
        switch (name){
            case "doctor" :{
                Doctor doctor= doctorService.getUserById(adminId);
                doctor.getRoles().add(Role.ADMIN);
                doctorService.updateUser(adminId,doctor);
                Admin admin = Admin.builder()
                        .adminId(adminId)
                        .build();
                adminRepo.save(admin);
            }

            case "pharmacist":{
                Pharmacist pharmacist= pharmacistService.getUserById(adminId);
                pharmacist.getRoles().add(Role.ADMIN);
                pharmacistService.updateUser(adminId,pharmacist);
                Admin admin = Admin.builder()
                        .adminId(adminId)
                        .build();
                adminRepo.save(admin);
            }

            case "reception":{
                Receptionist receptionist= receptionistService.getUserById(adminId);
                receptionist.getRoles().add(Role.ADMIN);
                receptionistService.updateUser(adminId,receptionist);
                Admin admin = Admin.builder()
                        .adminId(adminId)
                        .build();
                adminRepo.save(admin);
            }
        }
        return "updated";
    }


    public String removeAdmin(String adminId) {
        List<String> user = adminRepo.findByUserId(adminId);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid user");
        }

        String name = user.getClass().getName().toLowerCase(Locale.ROOT);
        switch (name){
            case "doctor" :{
                Doctor doctor= doctorService.getUserById(adminId);
                doctor.getRoles().remove(Role.ADMIN);
                doctorService.updateUser(adminId,doctor);
            }

            case "pharmacist":{
                Pharmacist pharmacist= pharmacistService.getUserById(adminId);
                pharmacist.getRoles().remove(Role.ADMIN);
                pharmacistService.updateUser(adminId,pharmacist);
            }

            case "reception":{
                Receptionist receptionist= receptionistService.getUserById(adminId);
                receptionist.getRoles().remove(Role.ADMIN);
                receptionistService.updateUser(adminId,receptionist);
            }
        }
        return "updated";
    }


    public String getWorkerById(String userId) throws UsernameNotFoundException {
        List<String> user = adminRepo.findByUserId(userId);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }
        return "user";

    }

    public Patient getPatientsByPatientId(String userId) throws NoSuchElementException {
        adminRepo.findByUserId(userId);
        return null;

    }

    public List<User> getAllUser() {
        return adminRepo.getAll();
    }

    public Admin updateUser(String userUd, Admin updatedUser) throws NoSuchElementException {
        return null;
    }

    public void deleteUser(String userId) throws NoSuchElementException {

    }

}
