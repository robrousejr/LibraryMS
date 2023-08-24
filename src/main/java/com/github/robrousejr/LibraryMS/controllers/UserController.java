package com.github.robrousejr.LibraryMS.controllers;

import com.github.robrousejr.LibraryMS.models.User;
import com.github.robrousejr.LibraryMS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /users – List all users (admin only)
    @GetMapping
    public List<User> getAllUsers() {
        // TODO: Limit access to Role.ADMIN
        return userService.getAllUsers();
    }

    // GET /users/{id} – Get a specific user (admin or the user itself)
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {

        // TODO: Limit access to Role.ADMIN or user itself
        return userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found"));
    }

    // POST /users – Register a new user
    @PostMapping
    public User registerNewUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // PUT /users/{id} – Update a user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    // DELETE /users/{id} – Delete a user (admin only)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

        // TODO: Limit access to Role.ADMIN

        userService.deleteUser(id);
    }
}
