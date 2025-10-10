package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Receptionist;
import com.mcmanuel.HospitalManagementSystem.service.intf.ReceptionistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
@RequiredArgsConstructor
public class ReceptionistServiceImpl implements ReceptionistService {
    @Override
    public Receptionist getUserById(String userId) throws NoSuchElementException {
        return null;
    }

    @Override
    public List<Receptionist> getAllUser() {
        return List.of();
    }

    @Override
    public Receptionist updateUser(String userUd, Receptionist updatedUser) throws NoSuchElementException {
        return null;
    }

    @Override
    public void deleteUser(String userId) throws NoSuchElementException {

    }
}
