package com.mcmanuel.HospitalManagementSystem.service.intf;

import java.util.List;
import java.util.NoSuchElementException;

public interface UserService <T>{

    T getUserById(Integer userId) throws NoSuchElementException;
    List<T> getAllUser();
    T updateUser(Integer userUd,T updatedUser) throws NoSuchElementException;
    void deleteUser(Integer userId) throws NoSuchElementException;
}
