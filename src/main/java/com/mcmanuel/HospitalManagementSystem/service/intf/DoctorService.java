package com.mcmanuel.HospitalManagementSystem.service.intf;


import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.service.request.AddDoctorRequest;

import java.util.List;
import java.util.UUID;

public interface DoctorService extends UserService<Doctor>{

    Doctor addDoctor(AddDoctorRequest addDoctorRequest);
    List<Doctor> getAvailableDoctors(String specialty);
    List<Patient> assignedPatients(Integer doctorsId) throws Exception;
}
