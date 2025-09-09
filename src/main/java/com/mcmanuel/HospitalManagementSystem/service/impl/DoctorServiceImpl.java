package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import com.mcmanuel.HospitalManagementSystem.service.intf.DoctorService;
import com.mcmanuel.HospitalManagementSystem.repository.DoctorRepository;
import com.mcmanuel.HospitalManagementSystem.service.request.DoctorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{
    private final DoctorRepository doctorRepo;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public Doctor addDoctor(DoctorRequest doctorRequest) {

        Doctor doctor = Doctor
                .builder()
                .email(doctorRequest.getEmail())
                .role(Role.DOCTOR)
                .password(
                        passwordEncoder.encode(doctorRequest.getPassword())
                )

                .specialization(doctorRequest.getSpecialization())
                .contact(doctorRequest.getContact())
                .build();
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());

        return doctorRepo.save(doctor);
    }


    @Override
    public Doctor getUserById(String doctorId) throws NoSuchElementException {
        return doctorRepo.findById(doctorId).orElseThrow();
    }

    @Override
    public List<Doctor> getAllUser() {
        return doctorRepo.findAll();
    }

    @Override
    public Doctor updateUser(String doctorId, Doctor updatedDoctor) throws NoSuchElementException {
        doctorRepo.findById(doctorId).orElseThrow();
        updatedDoctor.setUserId(doctorId);
        updatedDoctor.setPassword(
                passwordEncoder.encode(updatedDoctor.getPassword())
        );

        return doctorRepo.save(updatedDoctor);
    }

    @Override
    public void deleteUser(String userId) throws NoSuchElementException {
        Doctor doctor = doctorRepo.findById(userId).orElseThrow();
        doctorRepo.delete(doctor);

    }

    @Override
    public List<Doctor> getAvailableDoctors(String specialty) {
        return doctorRepo.findAvailableDoctors(specialty);
    }

    @Override
    public List<Patient> assignedPatients(String doctorId) throws NoSuchElementException {
        Doctor doctor = doctorRepo.findById(doctorId).orElseThrow();
        doctorRepo.deleteById(doctor.getUserId());
        return List.of();
    }

    @Override
    public String updateAvailability(String doctorId) {
        var doctor = doctorRepo.findById(doctorId);
        if (doctor.isEmpty()) {
            return "Doctor not found";
        }
        doctor.get().setAvailable(!doctor.get().isAvailable());
        doctorRepo.save(doctor.get());
        return ("Doctor "+doctor.get().getFullName()+" Availability: "+doctor.get().isAvailable());
    }

}
