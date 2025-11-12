package com.mcmanuel.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "owner")
@PrimaryKeyJoinColumn(name = "user_id")
@SuperBuilder
@Getter
public class Owner extends User{

}
