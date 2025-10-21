package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.repository.PatientRepository;
import com.mcmanuel.HospitalManagementSystem.service.intf.DoctorService;
import com.mcmanuel.HospitalManagementSystem.service.intf.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepo;
    private final PasswordEncoder passwordEncoder;
    private final DoctorService doctorService;
    private ChatModel chatModel;

    @Override
    public Patient registerPatient(Patient patient) {

        patient.setPassword( passwordEncoder.encode(patient.getPassword()));
        return patientRepo.save(patient);
    }

    @Override
    public Patient getUserById(String userId) throws NoSuchElementException {
        return patientRepo.findById(userId).orElseThrow();
    }

    @Override
    public Patient getUserByEmail(String email) throws NoSuchElementException {
        return patientRepo.findByEmail(email).orElseThrow();
    }

    @Override
    public List<Patient> getAllUser() {
        return patientRepo.findAll();
    }

    @Override
    public Patient updateUser(String userUd, Patient updatedUser) throws NoSuchElementException {
        Patient patient = getUserById(userUd);
        updatedUser.setUserId(patient.getUserId());
        return patientRepo.save(updatedUser);
    }

    @Override
    public void deleteUser(String userId) throws NoSuchElementException {
        Patient patient =getUserById(userId);
        patientRepo.deleteById(patient.getUserId());
    }

    @Override
    public Patient assignPatient(String patientId) throws Exception {

        List<Doctor> availableDoctor = doctorService.getAvailableDoctors();
        if (availableDoctor == null) {
            System.out.println("No doctor available");
        }

        assert availableDoctor != null;
        Doctor assignedDoctor = availableDoctor.get((int) (Math.random() * availableDoctor.size()));

        Patient patient = getUserById(patientId);
        patient.setAssignedDoctor(assignedDoctor);
        patientRepo.save(patient);

        doctorService.assignedPatients(assignedDoctor.getUserId());
         return null;
    }

}
