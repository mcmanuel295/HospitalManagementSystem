package com.mcmanuel.HospitalManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Doctor extends User{
    @Column(nullable = false)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "doctor_specialization",
            joinColumns = @JoinColumn(
                    name = "doctor_id_fk",referencedColumnName = "user_id"
            )
    )
    private Set<String> specialization;
    private boolean available;

    @JsonManagedReference
    @OneToMany(mappedBy = "assignedDoctor",fetch = FetchType.LAZY)
    private List<Patient> assignedPatients;


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
