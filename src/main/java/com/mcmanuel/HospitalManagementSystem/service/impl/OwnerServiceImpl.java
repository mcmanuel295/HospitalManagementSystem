package com.mcmanuel.HospitalManagementSystem.service.impl;

import com.mcmanuel.HospitalManagementSystem.entity.Owner;
import com.mcmanuel.HospitalManagementSystem.entity.User;
import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import com.mcmanuel.HospitalManagementSystem.repository.OwnerRepository;
import com.mcmanuel.HospitalManagementSystem.service.intf.AdminService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerServiceImpl {
    private final AdminService adminService;
    private final OwnerRepository ownerRepo;
    private final PasswordEncoder passwordEncoder;


    public Owner addOwner() throws EntityExistsException{
        Owner owner =createOwner();
        log.info("owner: {}",owner);
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
