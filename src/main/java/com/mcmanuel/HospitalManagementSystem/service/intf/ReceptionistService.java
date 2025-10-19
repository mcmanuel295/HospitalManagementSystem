package com.mcmanuel.HospitalManagementSystem.service.intf;

import com.mcmanuel.HospitalManagementSystem.entity.Receptionist;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;


public interface ReceptionistService extends UserService<Receptionist>{
    Receptionist addReceptionist(@RequestBody @Validated Receptionist receptionist);
}
