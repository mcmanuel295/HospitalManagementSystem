package com.mcmanuel.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @Column(name = "medicine_id")
    private String medicineId;

    @Column(name = "medicine_name",unique = true)
    private String medicineName;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "distributor",
            joinColumns = @JoinColumn(
                    name = "medicine_id_fk",
                    referencedColumnName = "medicine_id"
            )
    )
    private Set<String> distributor ;

    @Min(value = 0)
    private int quantity;

}
