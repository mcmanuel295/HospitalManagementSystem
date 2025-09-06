package com.mcmanuel.HospitalManagementSystem.service.request;

import lombok.Data;

@Data
public class PharmacistRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String contact;
}
