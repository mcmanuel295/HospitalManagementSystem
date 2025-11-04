package com.mcmanuel.HospitalManagementSystem.service.intf;

import com.mcmanuel.HospitalManagementSystem.entity.Patient;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public interface PatientService{
    Patient registerPatient(Patient patient);

    Patient getPatientById(String userId) throws NoSuchElementException;

    Patient getUserByEmail(String email) throws NoSuchElementException;

    List<Patient> getAllPatients();

    List<Map<String,String>> getAllPatientsWithAssignedDoctor();

    Patient updatePatient(String userUd, Patient updatedUser) throws NoSuchElementException;

    void deletePatient(String userId) throws NoSuchElementException;

    String assignPatient(String patientId) throws Exception;

    String unAssignPatient(String patientId) throws Exception;
}
