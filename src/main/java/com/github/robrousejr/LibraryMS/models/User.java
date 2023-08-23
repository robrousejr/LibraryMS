package com.github.robrousejr.LibraryMS.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @Setter
    private String email;

    @Setter
    private String password;

    @Setter
    @ManyToOne
    @JoinColumn(name = "role_id")
    @Enumerated(EnumType.STRING)
    @JsonView(Role.class)
    private Role role;
}
