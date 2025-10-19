package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.repository.PatientRepository;
import com.mcmanuel.HospitalManagementSystem.service.intf.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Patient registerPatient(Patient patient) {
        patient.setPassword( passwordEncoder.encode(patient.getPassword()));
        return patientRepo.save(patient);
    }

    @Override
    public Patient getUserById(String userId) throws NoSuchElementException {
        return null;
    }

    @Override
    public Patient getUserByEmail(String email) throws NoSuchElementException {
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
