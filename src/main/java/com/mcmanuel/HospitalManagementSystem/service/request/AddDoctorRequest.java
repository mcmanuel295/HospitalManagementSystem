package com.mcmanuel.HospitalManagementSystem.service.request;

import lombok.Data;

import java.util.List;

@Data
public class AddDoctorRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<String> specialization;
    private String contact;
}
