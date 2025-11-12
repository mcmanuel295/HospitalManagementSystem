package com.mcmanuel.HospitalManagementSystem.service.intf;

import java.util.List;
import java.util.NoSuchElementException;

public interface UserService <T>{cls
    T getUserById(String userId) throws NoSuchElementException;
    T getUserByEmail(String email) throws NoSuchElementException;
    List<T> getAllUser();
    T updateUser(String userId,T updatedUser) throws NoSuchElementException;
    void deleteUser(String userId) throws NoSuchElementException;
}
