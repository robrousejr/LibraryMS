package com.github.robrousejr.LibraryMS.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.robrousejr.LibraryMS.util.Views;
import jakarta.persistence.*;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.IdOnly.class)
    @Setter
    private Long id;

    @Enumerated(EnumType.STRING)
    @Setter
    private RoleName roleName;

    @OneToMany(mappedBy = "role")
    private Set<User> users; // assuming the other entity is named User

    // Pre-Defined Static Roles (for convenience)
    public static final Role ADMIN = createRole(1L, RoleName.Admin);
    public static final Role USER = createRole(2L, RoleName.User);

    private static Role createRole(Long id, RoleName roleName) {
        Role role = new Role();
        role.setId(id);
        role.setRoleName(roleName);
        return role;
    }

    public enum RoleName {
        Admin, User
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                roleName == role.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

}