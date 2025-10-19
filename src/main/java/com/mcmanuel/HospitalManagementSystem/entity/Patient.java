package com.mcmanuel.HospitalManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "patient")
public class Patient{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(unique = true,nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 50)
    private Role role =Role.PATIENT;

    private String contact;

    private List<String> problem;

    @Column(name = "full_name")
    private String fullName ;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        setFullName();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        setFullName();
    }

    public void setFullName() {
        this.fullName =this.getLastName()+" "+getFirstName();
    }

}
