package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Doctor;
import com.mcmanuel.HospitalManagementSystem.entity.Owner;
import com.mcmanuel.HospitalManagementSystem.repository.OwnerRepository;
import com.mcmanuel.HospitalManagementSystem.service.intf.AdminService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl {
    private final AdminService adminService;
    private final OwnerRepository ownerRepo;


    @PostConstruct
    private void automaticAdding(){

        adminService.addAdminByEmail(
                addOwner(
                        Owner.builder()
                                .firstName("Emmanuel")
                                .lastName("Ogbu")
                                .email("mcmanuel755@gmail.com")
                                .password("OE123")
                                .contact("09081199688")
                                .build()
                )
                        .getEmail()
        );
    }

    private Owner addOwner(Owner owner){

    }
}
