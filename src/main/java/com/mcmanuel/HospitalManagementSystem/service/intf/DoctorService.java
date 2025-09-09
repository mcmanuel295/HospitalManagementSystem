package com.mcmanuel.HospitalManagementSystem.service.intf;


import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.service.request.DoctorRequest;
import java.util.List;

public interface DoctorService extends UserService<Doctor>{

    Doctor addDoctor(DoctorRequest addDoctorRequest);
    List<Doctor> getAvailableDoctors(String specialty);
    List<Patient> assignedPatients(String doctorsId) throws Exception;
    String updateAvailability(String doctorId);
}
