package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.service.intf.DoctorService;
import com.mcmanuel.HospitalManagementSystem.repository.DoctorRepository;
import com.mcmanuel.HospitalManagementSystem.service.request.AddDoctorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{
    private final DoctorRepository doctorRepo;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public Doctor addDoctor(AddDoctorRequest doctorRequest) {
        Doctor doctor = Doctor
                .builder()
                .firstName(doctorRequest.getFirstName())
                .lastName(doctorRequest.getLastName())
                .email(doctorRequest.getEmail())

                .password(
                        passwordEncoder.encode(doctorRequest.getPassword())
                )

                .specialization(doctorRequest.getSpecialization())
                .contact(doctorRequest.getContact())
                .build();

        return doctorRepo.save(doctor);
    }


    @Override
    public Doctor getUserById(UUID doctorId) throws NoSuchElementException {
        return doctorRepo.findById(doctorId).orElseThrow();
    }

    @Override
    public List<Doctor> getAllUser() {
        return doctorRepo.findAll();
    }

    @Override
    public Doctor updateUser(UUID doctorId, Doctor updatedDoctor) throws NoSuchElementException {
        doctorRepo.findById(doctorId).orElseThrow();
        updatedDoctor.setUserId(doctorId);
        return updatedDoctor;
    }

    @Override
    public void deleteUser(UUID userId) throws NoSuchElementException {
        Doctor doctor = doctorRepo.findById(userId).orElseThrow();
        doctorRepo.delete(doctor);

    }

    @Override
    public Doctor getAvailableDoctors(String specialty) {
        return doctorRepo.findAvailableDoctors(specialty);
    }

    @Override
    public List<Patient> assignedPatients(UUID doctorId) throws NoSuchElementException {
        Doctor doctor = doctorRepo.findById(doctorId).orElseThrow();
        doctorRepo.deleteById(doctor.getUserId());
        return List.of();
    }

}
