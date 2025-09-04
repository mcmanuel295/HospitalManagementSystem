package com.mcmanuel.HospitalManagementSystem.service.intf;


import com.mcmanuel.HospitalManagementSystem.entity.Pharmacist;

import java.util.UUID;

public interface PharmacistService{

    Pharmacist addPharmacist(Pharmacist pharmacist);
    Pharmacist getPharmacist(UUID pharmacistId);
    Pharmacist updatePharmacist(Pharmacist updatedPharmacist);
    void deletePharmacist(UUID pharmacistId);
}
