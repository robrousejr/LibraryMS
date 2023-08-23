package com.github.robrousejr.LibraryMS.repositories;

import com.github.robrousejr.LibraryMS.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
