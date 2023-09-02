package com.github.robrousejr.LibraryMS.util;

import com.github.robrousejr.LibraryMS.models.Role;
import com.github.robrousejr.LibraryMS.models.User;
import com.github.robrousejr.LibraryMS.repositories.RoleRepository;
import com.github.robrousejr.LibraryMS.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
public class DbInitDev {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    private void postConstruct() {

        // Add ADMIN, USER roles
        Role adminRole = Role.ADMIN;
        Role userRole = Role.USER;
        roleRepository.saveAll(List.of(adminRole, userRole));

        // Add 2 users, 1 admin and 1 user
        User adminUser = new User(1L, "admin", "admin@gmail.com", "pass", adminRole);
        User normalUser = new User(2L, "user", "user@gmail.com", "pass", userRole);
        userService.saveAll(List.of(adminUser, normalUser));
    }
}