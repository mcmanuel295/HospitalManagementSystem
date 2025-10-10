package com.mcmanuel.HospitalManagementSystem.controller;

import com.mcmanuel.HospitalManagementSystem.service.intf.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
