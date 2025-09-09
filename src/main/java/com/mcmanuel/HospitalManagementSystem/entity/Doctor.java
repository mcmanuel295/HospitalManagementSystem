package com.mcmanuel.HospitalManagementSystem.entity;

import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Doctor extends User{
    @Enumerated(EnumType.STRING)
    private Role role = Role.DOCTOR;

    @Column(nullable = false)

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> specialization;
    private boolean available;

    @Override
    public String toString() {
        return "Doctor{" +
                "fullName :" + getFullName() +
                "email :" + getEmail() +
                "password :"+getPassword()+
                ", specialization :" + specialization +
                '}';
    }
}
