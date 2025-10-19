package com.mcmanuel.HospitalManagementSystem.service.intf;


import com.mcmanuel.HospitalManagementSystem.entity.Patient;

import java.util.List;

public interface AdminService {

    String addAdmin(String adminId);

    String removeAdmin(String adminId);

    List<Patient> getAllPatients();
}
