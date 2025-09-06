package com.mcmanuel.HospitalManagementSystem.entity;

import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Pharmacist extends User{
    @Enumerated(EnumType.STRING)
    private Role role = Role.PHARMACIST;

}
