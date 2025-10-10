package com.mcmanuel.HospitalManagementSystem.request;

import lombok.Data;

import java.util.Set;

@Data
public class DoctorRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<String> specialization;
    private String contact;
}
