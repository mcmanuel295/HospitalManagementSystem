package com.mcmanuel.HospitalManagementSystem.service.intf;

import com.mcmanuel.HospitalManagementSystem.entity.Patient;

import java.util.UUID;

public interface PatientService {

    Patient addPatient(Patient patient);
    Patient getPatient(UUID patientId);
    Patient updatePatient(Patient updatedPatient);
    void deletePatient(UUID patientId);
}
