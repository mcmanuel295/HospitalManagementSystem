package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Owner;
import com.mcmanuel.HospitalManagementSystem.entity.User;
import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import com.mcmanuel.HospitalManagementSystem.repository.OwnerRepository;
import com.mcmanuel.HospitalManagementSystem.service.intf.AdminService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class OwnerServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(OwnerServiceImpl.class);
    private final AdminService adminService;
    private final OwnerRepository ownerRepo;
    private final PasswordEncoder passwordEncoder;


    private OwnerServiceImpl(AdminService adminService, OwnerRepository ownerRepo, PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.ownerRepo = ownerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void automaticAdding(){
        try {
            adminService.addAdminByEmail(addOwner().getEmail());
        }
        catch (EntityExistsException ex){
            log.error("Entity already exist{}",ex.getMessage());
        }
        catch (Exception ex){
            log.error("error occurred: {}",ex.getMessage());
        }
    }

    private Owner addOwner(){
        Owner owner =createOwner();
        System.out.println("owner :"+owner);
        return ownerRepo.save(owner);
    }

    private Owner createOwner(){
        boolean exist =adminService.getAllUser()
                .stream()
                .map(User::getEmail)
                .filter( email-> email.equals("mcmanuel755@gmail.com")
                )
                .toList()
                .isEmpty();


        if (exist){
            Owner owner=Owner.builder()
                    .email("mcmanuel755@gmail.com")
                    .password(
                            passwordEncoder.encode("OE123")
                    )
                    .roles(new HashSet<>())
                    .contact("09081199688")
                    .dateCreated(LocalDateTime.now())
                    .department(Owner.class.getSimpleName())
                    .build();
            owner.setFirstName("Emmanuel");
            owner.setLastName("Ogbu");
            owner.getRoles().add(Role.OWNER);

            ownerRepo.save(owner);
            return owner;
        }
        throw new EntityExistsException();
    }
}
