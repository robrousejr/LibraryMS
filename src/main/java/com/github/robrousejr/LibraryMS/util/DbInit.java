package com.github.robrousejr.LibraryMS.util;

import com.github.robrousejr.LibraryMS.models.Role;
import com.github.robrousejr.LibraryMS.models.User;
import com.github.robrousejr.LibraryMS.repositories.RoleRepository;
import com.github.robrousejr.LibraryMS.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbInit {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    private void postConstruct() {

        // Add 2 roles, Admin and User
        // TODO: At some point, only do this for DEV environments
        // TODO: In prod, only create the roles and 1 user (admin)
        Role adminRole = Role.ADMIN;
        Role userRole = Role.USER;
        roleRepository.saveAll(List.of(adminRole, userRole));

        // Add 2 users, 1 admin and 1 user
        User adminUser = new User(1L, "Admin", "admin@gmail.com", "pass", adminRole);
        User normalUser = new User(2L, "User", "user@gmail.com", "pass", userRole);
        userRepository.saveAll(List.of(adminUser, normalUser));
    }
}