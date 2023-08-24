package com.github.robrousejr.LibraryMS.services;

import com.github.robrousejr.LibraryMS.LibraryMSApplication;
import com.github.robrousejr.LibraryMS.models.Role;
import com.github.robrousejr.LibraryMS.models.User;
import com.github.robrousejr.LibraryMS.repositories.IssuedBookRepository;
import com.github.robrousejr.LibraryMS.repositories.UserRepository;
import com.github.robrousejr.LibraryMS.util.TestHelper;
import lombok.ToString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ToString
@SpringBootTest(classes = LibraryMSApplication.class)
@Import({UserService.class, BCryptPasswordEncoder.class})
public class UserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssuedBookRepository issuedBookRepository;

    @Autowired
    private UserService userService;

    // TODO: if this works, apply it to BookServiceIntegrationTest
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void cleanDatabase() {
        issuedBookRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testGetAllUsersWithUsers() {

        // Add 3 users
        List<User> users = TestHelper.buildUserSeries(3);
        userService.saveAll(users);

        // Find all users
        List<User> actualUsers = userService.getAllUsers();

        // Validate that only 3 users exist
        assertEquals(3, actualUsers.size());

        // Validate that the users are the ones we added
        assertTrue(TestHelper.compareUserLists(users, actualUsers));
    }

    @Test
    public void testGetAllUsersWithNoUsers() {

        // Find all users
        List<User> actualUsers = userService.getAllUsers();

        // Validate that no users exist
        assertEquals(0, actualUsers.size());
    }

    @Test
    public void testGetUserByIdWithUser() {

        // Add a user
        User user = TestHelper.buildUser();
        userService.saveUser(user);

        // Find the user by ID
        User actualUser = userService.getUserById(user.getId()).get();

        // Validate that the user is the one we added
        assertTrue(TestHelper.compareUsers(user, actualUser));
    }

    @Test
    public void testGetUserByIdWithNoUser() {

        // Find the user by ID
        User actualUser = userService.getUserById(1L).orElse(null);

        // Validate that no user exists
        assertNull(actualUser);
    }

    @Test
    public void testSaveAllUsers() {

        // Add 3 users
        List<User> users = TestHelper.buildUserSeries(3);
        List<User> actualUsers = userService.saveAll(users);

        // Validate that only 3 users exist
        assertEquals(3, actualUsers.size());

        // Validate that the users are the ones we added
        assertTrue(TestHelper.compareUserLists(users, actualUsers));
    }

    @Test
    public void testSaveUser() {

        // Add a user
        User user = TestHelper.buildUser();
        User actualUser = userService.saveUser(user);

        // Validate that the user is the one we added
        assertTrue(TestHelper.compareUsers(user, actualUser));
    }

    @Test
    public void deleteUser() {

        // Add a user
        User user = TestHelper.buildUser();
        userService.saveUser(user);

        // Verify user exists
        assertTrue(userService.getUserById(user.getId()).isPresent());

        // Delete the user
        userService.deleteUser(user.getId());

        // Validate that the user no longer exists
        assertFalse(userService.getUserById(user.getId()).isPresent());
    }

    @Test
    public void updateUser() {

        // Add a user
        User user = TestHelper.buildUser();
        userService.saveUser(user);

        // Verify user exists
        assertTrue(userService.getUserById(user.getId()).isPresent());

        // Update the user
        User updatedUser = TestHelper.buildUser();
        updatedUser.setName("Updated Name");
        updatedUser.setEmail("Updated Email");
        updatedUser.setPassword("Updated Password");
        updatedUser.setRole(Role.ADMIN);
        User actualUser = userService.updateUser(user.getId(), updatedUser);

        // Validate that the user is the one we updated
        assertTrue(TestHelper.compareUsers(updatedUser, actualUser));
    }


}