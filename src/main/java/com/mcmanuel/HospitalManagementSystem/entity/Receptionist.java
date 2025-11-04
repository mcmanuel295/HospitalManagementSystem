package com.mcmanuel.HospitalManagementSystem.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "receptionist")
public class Receptionist extends User{
}
