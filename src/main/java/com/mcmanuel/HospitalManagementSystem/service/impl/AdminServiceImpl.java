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
    public String addAdmin(String userId) throws UsernameNotFoundException {

        Optional<String> optionalDepartment= adminRepo.getUserDepartment(userId);
        if (optionalDepartment.isEmpty()) {
            throw new UsernameNotFoundException("Invalid user");
        }

       String name  =optionalDepartment.get().toLowerCase();

        switch (name){
            case "doctor" :{
                Doctor doctor= doctorService.getUserById(userId);
                doctor.getRoles().add(Role.ADMIN);
                doctorService.updateUser(doctor.getUserId(),doctor);
                Admin admin = Admin.builder()
                        .adminId(doctor.getUserId())
                        .build();
                adminRepo.save(admin);
                break;
            }

            case "pharmacist":{
                Pharmacist pharmacist= pharmacistService.getUserById(userId);
                pharmacist.getRoles().add(Role.ADMIN);
                pharmacistService.updateUser(pharmacist.getUserId(),pharmacist);
                Admin admin = Admin.builder()
                        .adminId(pharmacist.getUserId())
                        .build();
                adminRepo.save(admin);
                break;
            }

            case "receptionist":{
                Receptionist receptionist= receptionistService.getUserById(userId);
                receptionist.getRoles().add(Role.ADMIN);
                receptionistService.updateUser(receptionist.getUserId(),receptionist);
                Admin admin = Admin.builder()
                        .adminId(receptionist.getUserId())
                        .build();
                adminRepo.save(admin);
            }
        }
        return "updated";
    }


    public String removeAdmin(String adminId) {
        Optional<String> optionalDepartment= adminRepo.getUserDepartment(adminId);

        if (optionalDepartment.isEmpty() || adminRepo.findById(adminId).isEmpty()) {
            throw new UsernameNotFoundException("Invalid user or admin");
        }

        String name  =optionalDepartment.get().toLowerCase();

        switch (name){
            case "doctor" :{
                Doctor doctor= doctorService.getUserById(adminId);
                doctor.getRoles().remove(Role.ADMIN);
                doctorService.updateUser(adminId,doctor);
                adminRepo.deleteById(doctor.getUserId());
            }

            case "pharmacist":{
                Pharmacist pharmacist= pharmacistService.getUserById(adminId);
                pharmacist.getRoles().remove(Role.ADMIN);
                pharmacistService.updateUser(adminId,pharmacist);
                adminRepo.deleteById(pharmacist.getUserId());
            }

            case "reception":{
                Receptionist receptionist= receptionistService.getUserById(adminId);
                receptionist.getRoles().remove(Role.ADMIN);
                receptionistService.updateUser(adminId,receptionist);
                adminRepo.deleteById(receptionist.getUserId());
            }
        }
        return "updated";
    }


    @Override
    public User getWorkerById(String userId) throws UsernameNotFoundException {
        Optional<String> department = adminRepo.getUserDepartment(userId);

        if(department.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }

        return switch (department.get().toLowerCase()) {
            case "doctor" -> doctorService.getUserById(userId);
            case "receptionist" -> receptionistService.getUserById(userId);
            case "pharmacist" -> pharmacistService.getUserById(userId);
            default -> throw new UsernameNotFoundException("User not found");
        };

    }

    /** This returns all the workers not just admins*/
    @Override
    public List<User> getAllUser() {
        return adminRepo.getAll();
    }



//  /** This updates a worker not user*/
//    public Admin updateUser(String userId, User updatedUser) throws NoSuchElementException {
//        User user = getWorkerById(userId);
//
//        switch (user.getDepartment().toLowerCase()) {
//            case "doctor" -> doctorService.updateUser()
//            case "receptionist" -> receptionistService.getUserById(userId);
//            case "pharmacist" -> pharmacistService.getUserById(userId);
//            default -> throw new UsernameNotFoundException("User not found");
//        };
//        return null;
//    }


    /** This deletes a worker not a patient*/
    @Override
    public void deleteUser(String userId) throws NoSuchElementException {
        User user = getWorkerById(userId);

        switch (user.getDepartment().toLowerCase()) {
            case "doctor" -> doctorService.deleteUser(user.getUserId());
            case "receptionist" -> receptionistService.deleteUser(user.getUserId());
            case "pharmacist" -> pharmacistService.getUserById(user.getUserId());
            default -> throw new UsernameNotFoundException("User not found");
        };
    }

}
