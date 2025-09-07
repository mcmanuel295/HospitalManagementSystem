package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Receptionist;
import com.mcmanuel.HospitalManagementSystem.service.intf.ReceptionistService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class ReceptionistServiceImpl implements ReceptionistService {
    @Override
    public Receptionist getUserById(Integer userId) throws NoSuchElementException {
        return null;
    }

    @Override
    public List<Receptionist> getAllUser() {
        return List.of();
    }

    @Override
    public Receptionist updateUser(Integer userUd, Receptionist updatedUser) throws NoSuchElementException {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) throws NoSuchElementException {

    }
}
