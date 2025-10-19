package com.mcmanuel.HospitalManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "pharmacist")
public class Pharmacist extends User{

}
