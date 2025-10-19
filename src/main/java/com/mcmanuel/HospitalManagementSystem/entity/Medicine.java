package com.mcmanuel.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.HashSet;
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

    @Column(name = "medicine_name",unique = true)
    private String medicineName;

    @Min(value = 0)
    private int quantity;

    @ElementCollection
    private Set<String> distributor = new HashSet<>();


}
