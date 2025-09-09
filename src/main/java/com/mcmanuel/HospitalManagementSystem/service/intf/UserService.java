package com.mcmanuel.HospitalManagementSystem.service.intf;

import java.util.List;
import java.util.NoSuchElementException;

public interface UserService <T>{

    T getUserById(String userId) throws NoSuchElementException;
    List<T> getAllUser();
    T updateUser(String userUd,T updatedUser) throws NoSuchElementException;
    void deleteUser(String userId) throws NoSuchElementException;
}
