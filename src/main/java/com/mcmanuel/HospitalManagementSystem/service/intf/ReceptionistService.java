package com.mcmanuel.HospitalManagementSystem.service.intf;

import com.mcmanuel.HospitalManagementSystem.entity.Receptionist;

import java.util.UUID;

public interface ReceptionistService {

    Receptionist addReceptionist(Receptionist receptionist);
    Receptionist getReceptionist(UUID receptionistId);
    Receptionist updateReceptionist(Receptionist updatedReceptionist);
    void deleteReceptionist(UUID receptionistId);
}
