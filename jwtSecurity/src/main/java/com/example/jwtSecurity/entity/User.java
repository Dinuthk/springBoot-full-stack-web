package com.example.jwtSecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    private String userName;
    private int age;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL) //CascadeType.ALL => If a User is saved, all its associated Roles will also be saved. , If a User is deleted, all its associated Roles will also be deleted.
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            })
    private Set<com.example.jwtSecurity.entity.Role> roles;

}
