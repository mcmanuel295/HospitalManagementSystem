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
    public Patient getPatientById(String userId) throws NoSuchElementException {
        return patientRepo.findById(userId).orElseThrow();
    }

    @Override
    public Patient getUserByEmail(String email) throws NoSuchElementException {
        return patientRepo.findByEmail(email).orElseThrow();
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    @Override
    public Patient updatePatient(String userUd, Patient updatedUser) throws NoSuchElementException {
        Patient patient = getPatientById(userUd);
        updatedUser.setUserId(patient.getUserId());
        return patientRepo.save(updatedUser);
    }

    @Override
    public void deletePatient(String userId) throws NoSuchElementException {
        Patient patient =getPatientById(userId);
        patientRepo.deleteById(patient.getUserId());
    }

    @Override
    public String assignPatient(String patientId) throws Exception {
        Patient patient = getPatientById(patientId);

//        TODO Get patients problems,compare which specialization fit s it


        List<Doctor> availableDoctor = doctorService.getAvailableDoctors("nurse");

        if (availableDoctor == null) {
            System.out.println("No doctor available");
        }

        assert availableDoctor != null;
        Doctor assignedDoctor = availableDoctor.get((int) (Math.random() * availableDoctor.size()));

        patient.setAssignedDoctor(assignedDoctor);
        assignedDoctor.getAssignedPatients().add(patient);
        assignedDoctor.setAvailable(!assignedDoctor.isAvailable());

//        TODO Notify the doctor

        doctorService.updateUser(assignedDoctor.getUserId(),assignedDoctor);
        patientRepo.save(patient);
        return "assigned to "+assignedDoctor.getFullName();
    }

    @Override
    public String unAssignPatient(String patientId) throws Exception {
        Patient patient = getPatientById(patientId);
        doctorService.unassignPatient(patient.getAssignedDoctor().getUserId(),patientId);
        patient.setAssignedDoctor(null);
        patientRepo.save(patient);

        return "Doctor unassigned from "+patient.getFullName();
    }

}
