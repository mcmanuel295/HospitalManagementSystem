package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import com.mcmanuel.HospitalManagementSystem.repository.PatientRepository;
import com.mcmanuel.HospitalManagementSystem.service.intf.DoctorService;
import com.mcmanuel.HospitalManagementSystem.repository.DoctorRepository;
import com.mcmanuel.HospitalManagementSystem.request.DoctorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public Doctor addDoctor(DoctorRequest doctorRequest) {

        Doctor doctor = Doctor
                .builder()
                .email(doctorRequest.getEmail())
                .password(
                        passwordEncoder.encode(doctorRequest.getPassword())
                )
                .roles(new HashSet<>())
                .specialization(doctorRequest.getSpecialization())
                .contact(doctorRequest.getContact())
                .department(Doctor.class.getSimpleName())
                .dateCreated(LocalDateTime.now())
                .build();
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());

        doctor.getRoles().add(Role.DOCTOR);

        return doctorRepo.save(doctor);
    }


    @Override
    public Doctor getUserById(String doctorId) throws NoSuchElementException {
        return doctorRepo.findById(doctorId).orElseThrow();
    }

    @Override
    public Doctor getUserByEmail(String email) throws NoSuchElementException {
        return doctorRepo.findByEmail(email);
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
    public List<Patient> getAssignedPateints(String doctorId) throws NoSuchElementException {
        Doctor doctor = doctorRepo.findById(doctorId).orElseThrow();
        return doctor.getAssignedPatients();
    }

    @Transactional
    @Override
    public void unassignPatient(String doctorId, String patientId)throws NoSuchElementException {
        Doctor doctor = doctorRepo.findById(doctorId).orElseThrow();
        Patient patient = patientRepo.findById(patientId).orElseThrow();

        System.out.println("Doctor is "+doctor+"\n Patient is "+patientId);

//        TODO Notify the receptionist

        if (!doctor.getAssignedPatients().contains(patient) ){
            throw new NoSuchElementException("Patient not found");
        }

        doctor.getAssignedPatients().remove(patient);
        doctor.setAvailable(!doctor.isAvailable());
        doctorRepo.save(doctor);
//        return patient.getFullName()+" unassigned to "+doctor.getFullName();
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

    @Override
    public Set<String> getAllSpecialty() {
        return doctorRepo.getAllSpecialty();
    }

}
