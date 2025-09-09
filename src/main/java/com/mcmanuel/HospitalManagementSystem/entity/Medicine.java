package com.mcmanuel.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String medicineId;

    @ElementCollection
    private String medicineName;

    @ElementCollection
    private Set<String> company;
    private Set<String> distributor;


}
