package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Receptionist;
import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import com.mcmanuel.HospitalManagementSystem.repository.ReceptionistRepository;
import com.mcmanuel.HospitalManagementSystem.service.intf.ReceptionistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceptionistServiceImpl implements ReceptionistService {
    private final ReceptionistRepository receptionistRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Receptionist addReceptionist(@RequestBody @Validated Receptionist receptionist){

        receptionist.setFirstName(receptionist.getFirstName());
        receptionist.setLastName(receptionist.getLastName());
        receptionist.setDepartment(Receptionist.class.getSimpleName());

        receptionist.setPassword(passwordEncoder.encode(receptionist.getPassword()));
        receptionist.setRoles(new HashSet<>());
        receptionist.getRoles().add(Role.RECEPTIONIST);

        return receptionistRepo.save(receptionist);
    }


    @Override
    public Receptionist getUserById(String userId) throws NoSuchElementException {
        return receptionistRepo.findById(userId).orElseThrow();
    }

    @Override
    public Receptionist getUserByEmail(String email) throws NoSuchElementException {
        return receptionistRepo.findByEmail(email).orElseThrow();
    }


    @Override
    public List<Receptionist> getAllUser() {
        return receptionistRepo.findAll();
    }

    @Override
    public Receptionist updateUser(String userId, Receptionist updatedUser) throws NoSuchElementException {
        Optional<Receptionist> savedReceptionist = receptionistRepo.findById(userId);

        if (savedReceptionist.isEmpty()){
            throw new NoSuchElementException();
        }
        updatedUser.setUserId( savedReceptionist.get().getUserId());
        return receptionistRepo.save(updatedUser);
    }

    @Override
    public void deleteUser(String userId) throws NoSuchElementException {
        Optional<Receptionist> savedReceptionist = receptionistRepo.findById(userId);

        if (savedReceptionist.isEmpty()){
            throw new NoSuchElementException();
        }

        receptionistRepo.deleteById(userId);
    }
}
