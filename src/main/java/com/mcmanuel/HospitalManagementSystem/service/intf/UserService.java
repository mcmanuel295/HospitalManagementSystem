package com.mcmanuel.HospitalManagementSystem.service.intf;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface UserService <T>{

    T getUserById(UUID userId) throws NoSuchElementException;
    List<T> getAllUser();
    T updateUser(UUID userUd,T updatedUser) throws NoSuchElementException;
    void deleteUser(UUID userId) throws NoSuchElementException;
}
