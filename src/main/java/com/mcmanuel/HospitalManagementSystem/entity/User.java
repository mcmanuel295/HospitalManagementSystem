package com.mcmanuel.HospitalManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mcmanuel.HospitalManagementSystem.pojo.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Column(name = "user_id")
    private String userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    @Column(unique = true,nullable = false)
    private String password;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"
            )
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 50)
    private Set<Role> roles;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String department;

    private String contact;

    @Column(name = "date_created", updatable = false,nullable = false)
    private LocalDateTime dateCreated;

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

