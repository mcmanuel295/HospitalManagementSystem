package com.mcmanuel.HospitalManagementSystem.service.intf;


import com.mcmanuel.HospitalManagementSystem.entity.Doctor;

import java.util.UUID;

public interface DoctorService{

    Doctor addDoctor(Doctor doctor);
    Doctor getDoctor(UUID doctorId);
    Doctor updateDoctor(Doctor updatedDoctor);
    void deleteDoctor(UUID doctorId);
}
