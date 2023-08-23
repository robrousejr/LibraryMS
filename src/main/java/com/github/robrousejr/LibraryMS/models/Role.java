package com.github.robrousejr.LibraryMS.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.IdOnly.class)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    @OneToMany(mappedBy = "role")
    private Set<User> users; // assuming the other entity is named User

    public enum RoleName {
        ADMIN, USER
    }
}