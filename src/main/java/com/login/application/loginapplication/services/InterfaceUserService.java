package com.login.application.loginapplication.services;

import java.util.List;
import java.util.Optional;

import com.login.application.loginapplication.models.User;

/**
 * The Interface InterfaceUserService. This interface is used to create a
 * service for the User object. This service is used to access the database and
 * perform CRUD (create, read, update, delete) operations on user objects.
 * 
 * @author Alex Koh
 */
public interface InterfaceUserService {
    /**
     * Gets a list of all users.
     * @return the list of all users
     */
    List<User> getUsers();

    /**
     * Finds a user by their username.
     * @param username the username of the user to find
     * @return an optional User object with the matching username, or an empty
     *         optional if not found
     */
    Optional<User> findByUsername(String username);
}
