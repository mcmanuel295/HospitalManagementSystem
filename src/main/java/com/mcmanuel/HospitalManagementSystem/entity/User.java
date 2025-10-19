package com.mcmanuel.HospitalManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(unique = true,nullable = false)
    private String password;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 50)
    private Set<Role> roles =new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String department;

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

