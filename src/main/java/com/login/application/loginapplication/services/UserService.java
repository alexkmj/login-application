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
 * This is the service for the User object. This service is used to access the
 * database.
 */
@Service
@RequiredArgsConstructor
public class UserService implements InterfaceUserService, UserDetailsService {
    /**
     * The user repository.
     */
    private final UserRepository userRepository;

    /**
     * Gets the users.
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Find by username.
     */
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Load user by username.
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
