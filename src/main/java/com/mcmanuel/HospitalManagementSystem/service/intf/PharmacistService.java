package com.mcmanuel.HospitalManagementSystem.service.intf;


import com.mcmanuel.HospitalManagementSystem.entity.Pharmacist;
import com.mcmanuel.HospitalManagementSystem.request.PharmacistRequest;


public interface PharmacistService extends UserService<Pharmacist>{

    Pharmacist addPharmacist(PharmacistRequest pharmacistRequest);
    // prescribe drugs to patients
}
