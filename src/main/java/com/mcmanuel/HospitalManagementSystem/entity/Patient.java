package com.mcmanuel.HospitalManagementSystem.entity;

import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "patient")
public class Patient extends User{
    @Enumerated(EnumType.STRING)
    private Role role = Role.PATIENT;
}
