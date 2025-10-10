package com.mcmanuel.HospitalManagementSystem.entity;

import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(unique = true,nullable = false)
    private String password;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 50)
    private List<Role> roles;

    private String contact;

    @Column(name = "full_name")
    private String fullName ;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        setFullName();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        setFullName();
    }

    public void setFullName() {
        this.fullName =this.getLastName()+" "+getFirstName();
    }

}

