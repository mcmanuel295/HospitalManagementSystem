package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.repository.PatientRepository;
import com.mcmanuel.HospitalManagementSystem.service.intf.DoctorService;
import com.mcmanuel.HospitalManagementSystem.service.intf.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepo;
    private final PasswordEncoder passwordEncoder;
    private final DoctorService doctorService;
    private final ChatModel chatModel;

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

        final String specialty = getString(patient);

        List<Doctor> availableDoctor = doctorService.getAvailableDoctors(specialty);
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
//        return specialty.trim().replaceAll("[^a-zA-Z\\s-]", "");
    }


    private String getString(Patient patient) {
        PromptTemplate promptTemplate = new PromptTemplate("""
            You are a medical assignment specialist. Your sole job is to match a patient's problems 
            to the single most appropriate medical specialty from the 'Available Specialties' list.

            Available Specialties: {specialties}

            Patient Problems: {problems}

            **CRITICAL INSTRUCTION: Respond ONLY with the name of the specialty. Do NOT include 
            any other text, punctuation, explanation, or conversational fillers.**
            """);

        Map<String, Object> promptVariables = Map.of(
                "problems", patient.getProblem(),
                "specialties", doctorService.getAllSpecialty()
        );

        return chatModel.call(promptTemplate.render(promptVariables));
    }


    @Override
    public String unAssignPatient(String patientId) throws NoSuchElementException {
        Patient patient = getPatientById(patientId);
        doctorService.unassignPatient(patient.getAssignedDoctor().getUserId(),patientId);
        patient.setAssignedDoctor(null);
        patientRepo.save(patient);

        return "Doctor unassigned from "+patient.getFullName();
    }

}
