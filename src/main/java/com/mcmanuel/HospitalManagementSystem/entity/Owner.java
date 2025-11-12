package com.mcmanuel.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "owner")
@PrimaryKeyJoinColumn(name = "user_id")
@NoArgsConstructor
@SuperBuilder
@Getter
public class Owner extends User{

    @Override
    public String toString(){
        return this.getFullName()+"\n" + " "+this.getEmail();
    }
}
