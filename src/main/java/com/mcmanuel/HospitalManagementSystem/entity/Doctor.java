package com.mcmanuel.HospitalManagementSystem.entity;

import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Doctor extends User{
    @Enumerated(EnumType.STRING)
    private Role role = Role.DOCTOR;

    @Column(nullable = false)
    private String specialization;

    private String contact;
    private boolean available;


}
