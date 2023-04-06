package com.login.application.loginapplication.services;

import java.util.List;
import java.util.Optional;

import com.login.application.loginapplication.models.User;

/**
 * The Interface InterfaceUserService. This interface is used to create a
 * service for the User object. This service is used to access the database.
 */
public interface InterfaceUserService {
    List<User> getUsers();
    Optional<User> findByUsername(String username);
}
