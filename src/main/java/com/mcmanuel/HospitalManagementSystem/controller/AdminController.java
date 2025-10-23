package com.mcmanuel.HospitalManagementSystem.controller;

import com.mcmanuel.HospitalManagementSystem.entity.Patient;
import com.mcmanuel.HospitalManagementSystem.entity.User;
import com.mcmanuel.HospitalManagementSystem.service.intf.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("admins/")
public class AdminController {
    private final AdminService adminService;

    @PutMapping("/{adminId}/add")
    public ResponseEntity<String> addNewAdmin(@PathVariable String adminId){
        String admin = adminService.addAdmin(adminId);
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Added",HttpStatus.OK);
    }

    @PutMapping("/{adminId}/remove")
    public ResponseEntity<String> removeAdmin(@PathVariable String adminId){
        String admin = adminService.removeAdmin(adminId);
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Added",HttpStatus.OK);
    }

    @GetMapping("/users")
    ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(adminService.getAllUser(),HttpStatus.OK);
    }


}
