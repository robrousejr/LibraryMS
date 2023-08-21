package com.github.robrousejr.LibraryMS.repositories;
import com.github.robrousejr.LibraryMS.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
