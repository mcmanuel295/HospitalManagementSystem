package com.mcmanuel.HospitalManagementSystem.controller;

import com.mcmanuel.HospitalManagementSystem.entity.Receptionist;
import com.mcmanuel.HospitalManagementSystem.service.intf.ReceptionistService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/receptionists")
@RequiredArgsConstructor
public class ReceptionistController {
    private final ReceptionistService receptionistService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    ResponseEntity<Receptionist> addReceptionist(@RequestBody @Validated Receptionist receptionist){
        return new ResponseEntity<>(receptionistService.addReceptionist(receptionist), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}")
    ResponseEntity<Receptionist> getUserById(@PathVariable String userId) throws NoSuchElementException{
        Receptionist receptionist = receptionistService.getUserById(userId);
        if (receptionist==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(receptionist,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/email/{email}")
    ResponseEntity<Receptionist> getUserByEmail(String email) throws NoSuchElementException{
        Receptionist receptionist = receptionistService.getUserByEmail(email);
        if (receptionist==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(receptionist,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    ResponseEntity<List<Receptionist>> getAllUser(){
        return new ResponseEntity<>(receptionistService.getAllUser(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{userId}")
    ResponseEntity<Receptionist> updateUser(String userId,Receptionist updatedUser) throws NoSuchElementException{
        Receptionist receptionist = receptionistService.updateUser(userId,updatedUser);
        if (receptionist==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(receptionist,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{usedId}")
    ResponseEntity<String> deleteUser(String userId) throws NoSuchElementException{
        receptionistService.deleteUser(userId);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

}
