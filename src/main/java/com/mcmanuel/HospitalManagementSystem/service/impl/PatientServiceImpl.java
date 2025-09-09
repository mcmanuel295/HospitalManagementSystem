package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.service.intf.PatientService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class PatientServiceImpl implements PatientService {
    @Override
    public Patient getUserById(String userId) throws NoSuchElementException {
        return null;
    }

    @Override
    public List<Patient> getAllUser() {
        return List.of();
    }

    @Override
    public Patient updateUser(String userUd, Patient updatedUser) throws NoSuchElementException {
        return null;
    }

    @Override
    public void deleteUser(String userId) throws NoSuchElementException {

    }
}
