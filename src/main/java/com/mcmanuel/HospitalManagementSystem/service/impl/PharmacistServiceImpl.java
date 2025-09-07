package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import com.mcmanuel.HospitalManagementSystem.entity.Pharmacist;
import com.mcmanuel.HospitalManagementSystem.service.intf.PharmacistService;
import com.mcmanuel.HospitalManagementSystem.service.repository.PharmacistRepository;
import com.mcmanuel.HospitalManagementSystem.service.request.PharmacistRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PharmacistServiceImpl implements PharmacistService {
    private final PharmacistRepository pharmacistRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Pharmacist addPharmacist(PharmacistRequest pharmacistRequest) {
        Pharmacist pharmacist = Pharmacist
                .builder()
                .firstName(pharmacistRequest.getFirstName())
                .lastName(pharmacistRequest.getLastName())
                .email(pharmacistRequest.getEmail())
                .contact(pharmacistRequest.getContact())

                .password(
                        passwordEncoder.encode(pharmacistRequest.getPassword())
                )
                .build();

        return pharmacistRepo.save(pharmacist);
    }

    @Override
    public Pharmacist getUserById(Integer pharmacistId) throws NoSuchElementException {
        return pharmacistRepo.findById(pharmacistId).orElseThrow();
    }

    @Override
    public List<Pharmacist> getAllUser() {
        return pharmacistRepo.findAll();
    }

    @Override
        public Pharmacist updateUser(Integer pharmacistId, Pharmacist updatedPharmacist) throws NoSuchElementException {
        pharmacistRepo.findById(pharmacistId).orElseThrow();
        updatedPharmacist.setUserId(pharmacistId);
        return updatedPharmacist;
    }

    @Override
    public void deleteUser(Integer pharmacistId) throws NoSuchElementException {
        Pharmacist pharmacist = pharmacistRepo.findById(pharmacistId).orElseThrow();
        pharmacistRepo.delete(pharmacist);
    }

}
