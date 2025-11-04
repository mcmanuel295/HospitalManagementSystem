package com.mcmanuel.HospitalManagementSystem.service.intf;


import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.request.DoctorRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public interface DoctorService extends UserService<Doctor>{

    Doctor addDoctor(DoctorRequest addDoctorRequest);
    List<Doctor> getAvailableDoctors(String specialty);
    List<Patient> getAssignedPateints(String doctorsId) throws Exception;
    void unassignPatient(String doctorId,String patientId) throws NoSuchElementException;
    String updateAvailability(String doctorId);
    Set<String> getAllSpecialty();
}
