package com.mcmanuel.HospitalManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Medicine {
    @Id
    private UUID medicineId = UUID.randomUUID();

    private String name;
}
