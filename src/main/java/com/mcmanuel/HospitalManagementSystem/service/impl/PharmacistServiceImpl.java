package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Pharmacist;
import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import com.mcmanuel.HospitalManagementSystem.service.intf.PharmacistService;
import com.mcmanuel.HospitalManagementSystem.repository.PharmacistRepository;
import com.mcmanuel.HospitalManagementSystem.request.PharmacistRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PharmacistServiceImpl implements PharmacistService {
    private final PharmacistRepository pharmacistRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Pharmacist addPharmacist(PharmacistRequest pharmacistRequest) {
        Pharmacist pharmacist = Pharmacist
                .builder()
                .email(pharmacistRequest.getEmail())
                .roles(new HashSet<>())
                .department(Pharmacist.class.getSimpleName())
                .contact(pharmacistRequest.getContact())
                .password(
                        passwordEncoder.encode(pharmacistRequest.getPassword())
                )
                .build();

        pharmacist.setFirstName(pharmacistRequest.getFirstName());
        pharmacist.setLastName(pharmacistRequest.getLastName());
        pharmacist.getRoles().add(Role.PHARMACIST);
        return pharmacistRepo.save(pharmacist);
    }

    @Override
    public Pharmacist getUserById(String pharmacistId) throws NoSuchElementException {
        return pharmacistRepo.findById(pharmacistId).orElseThrow();
    }

    @Override
    public Pharmacist getUserByEmail(String email) throws NoSuchElementException {
        return pharmacistRepo.getByEmail(email);
    }

    @Override
    public List<Pharmacist> getAllUser() {
        return pharmacistRepo.findAll();
    }

    @Override
        public Pharmacist updateUser(String pharmacistId, Pharmacist updatedPharmacist) throws NoSuchElementException {
        pharmacistRepo.findById(pharmacistId).orElseThrow();
        updatedPharmacist.setUserId(pharmacistId);
        return updatedPharmacist;
    }

    @Override
    public void deleteUser(String pharmacistId) throws NoSuchElementException {
        Pharmacist pharmacist = pharmacistRepo.findById(pharmacistId).orElseThrow();
        pharmacistRepo.delete(pharmacist);
    }

}
