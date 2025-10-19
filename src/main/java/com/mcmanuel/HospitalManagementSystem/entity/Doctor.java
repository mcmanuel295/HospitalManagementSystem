package com.mcmanuel.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor extends User{
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
