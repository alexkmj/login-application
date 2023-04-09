package com.login.application.loginapplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.login.application.loginapplication.models.SecurityUser;
import com.login.application.loginapplication.models.User;
import com.login.application.loginapplication.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * This is the implementation of the @{link InterfaceUserService} for the
 * @{link User} object. This service is used to access the database and
 * implement the @{link UserDetailsService} interface provided by Spring
 * Security to load user details.
 *
 * The {@code @Service} annotation is used to mark this class as a service.
 *
 * The {@code @RequiredArgsConstructor} annotation is used to automatically
 * generate a constructor for this class that takes in all final fields and
 * initializes them.
 *
 * @see com.login.application.loginapplication.services.InterfaceUserService
 * @see org.springframework.security.core.userdetails.UserDetailsService
 *
 * @author Alex Koh
 */
@Service
@RequiredArgsConstructor
public class UserService implements InterfaceUserService, UserDetailsService {
    /**
     * The user repository.
     */
    private final UserRepository userRepository;

    /**
     * Gets a list of all users.
     * 
     * @return the list of all users
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Find a user by their username. This method is implemented from the
     * @{link InterfaceUserService} interface. If the user is not found, an
     * empty optional is returned.
     * 
     * @see com.login.application.loginapplication.services.InterfaceUserService
     * 
     * @param username the username of the user to find
     * @return an optional @{link User} object with the matching username, or an
     *         empty optional if not found
     */
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Loads a user by their username for Spring Security. This method is
     * implemented from the @{link UserDetailsService} interface. If the user is
     * not found, a UsernameNotFoundException is thrown. Otherwise, a
     * @{link SecurityUser} object is created and returned. This object is used
     * by Spring Security to authenticate the user.
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService
     * 
     * @param username the username of the user to find
     * @return a UserDetails object for Spring Security
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            String errorMsg = "User with username " + username + " not found";
            throw new UsernameNotFoundException(errorMsg);
        }

        return new SecurityUser(user.get());
    }

}
