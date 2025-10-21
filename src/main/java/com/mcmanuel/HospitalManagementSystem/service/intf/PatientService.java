package com.mcmanuel.HospitalManagementSystem.service.intf;

import com.mcmanuel.HospitalManagementSystem.entity.Patient;

import java.util.List;
import java.util.NoSuchElementException;

public interface PatientService{
    Patient registerPatient(Patient patient);

    Patient getUserById(String userId) throws NoSuchElementException;

    Patient getUserByEmail(String email) throws NoSuchElementException;

    List<Patient> getAllUser();

    Patient updateUser(String userUd, Patient updatedUser) throws NoSuchElementException;

    void deleteUser(String userId) throws NoSuchElementException;

    String assignPatient(String patientId) throws Exception;

    String unAssignPatient(String patientId) throws Exception;

}
