package com.login.application.loginapplication.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.login.application.loginapplication.models.SecurityUser;
import com.login.application.loginapplication.models.User;
import com.login.application.loginapplication.repositories.UserRepository;
import com.login.application.loginapplication.services.UserService;

import lombok.RequiredArgsConstructor;

/**
 * Controller for the user API.
 */
@RestController
@RequiredArgsConstructor
public class UserApiController {

    /**
     * The user service.
     */
    private final UserService userService;

    /**
     * The user repository.
     */
    private final UserRepository userRepository;

    /**
     * Get the current user.
     * 
     * @param user the user
     * @return Map with the user's name, username and if the user is a manager
     */
    @RequestMapping("/api/v1/user")
    public Map<String, Object> getUser(@AuthenticationPrincipal SecurityUser user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", user.getUser().getName());
        map.put("username", user.getUsername());
        map.put("isManager", user.getUser().isManager());

        return map;
    }

    /**
     * Get all users if the user is a manager. Otherwise return an empty list.
     * 
     * @param user the user
     * @return List of users
     */
    @RequestMapping("/api/v1/users")
    public List<User> getUsers(@AuthenticationPrincipal SecurityUser user) {
        if (!user.getUser().isManager()) {
            return new LinkedList<>();
        }

        return userService.getUsers();
    }

    /**
     * Create a new user if the username is not already taken.
     * 
     * @return Map with either an error or success message
     */
    @RequestMapping(value = "/api/v1/register", method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Object> createUser(@RequestBody Map<String, Object> payload) {
        Map<String, Object> map = new HashMap<String, Object>();

        String name = (String) payload.get("name");
        String username = (String) payload.get("username");
        String password = (String) payload.get("password");

        if (name == null || username == null || password == null) {
            map.put("error", "Missing parameters");
            return map;
        }

        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            map.put("error", "User already exists");
            return map;
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User newUser = new User(username, passwordEncoder.encode(password), name, false);
        userRepository.save(newUser);

        map.put("success", "User created");

        return map;
    }

    /**
     * Set the manager status of a user.
     * 
     * @param username
     */
    @RequestMapping(value = "/api/v1/user/manager", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public Map<String, Object> setManager(@RequestBody Map<String, Object> payload) {
        Map<String, Object> map = new HashMap<String, Object>();

        String username = (String) payload.get("username");

        Optional<User> user = userService.findByUsername(username);
        if (user.isEmpty()) {
            map.put("error", "User does not exist");
            return map;
        }

        user.get().setManager(true);
        userRepository.save(user.get());

        map.put("success", "User is now a manager");

        return map;
    }

    /**
     * Check if the user is currently logged in.
     * 
     */
    @RequestMapping(value = "/api/v1/user/loggedin", method = RequestMethod.GET)
    public Map<String, Object> isLoggedIn(@AuthenticationPrincipal SecurityUser user) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (user == null) {
            map.put("loggedin", false);
        } else {
            map.put("loggedin", true);
        }

        return map;
    }
}