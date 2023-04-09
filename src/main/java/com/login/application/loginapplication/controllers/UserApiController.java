package com.login.application.loginapplication.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.access.annotation.Secured;
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
 * Controller for the user API. This class handles incoming HTTP requests
 * related to users and interacts with the {@link UserService} and
 * {@link UserRepository} to perform CRUD operations on the `users` database
 * table.
 * 
 * The {@link @RestController} annotation is used to mark this class as a
 * controller.
 * 
 * The {@link @RequiredArgsConstructor} annotation is used to automatically
 * generate a constructor for this class that takes in all final fields and
 * initializes them.
 * 
 * @author Alex Koh
 */
@RestController
@RequiredArgsConstructor
public class UserApiController {

    /**
     * The user service, which provides an abstraction layer over the user
     * repository for performing business logic on user entities. This is
     * injected by Spring using the {@link RequiredArgsConstructor} annotation.
     */
    private final UserService userService;

    /**
     * The user repository, which provides low-level CRUD operations for
     * interacting with the user database table. This is injected by Spring
     * using the {@link RequiredArgsConstructor} annotation.
     */
    private final UserRepository userRepository;

    /**
     * Get all users if the authenticated user is a manager. Otherwise return an
     * empty list. This method is secured by the {@link Secured} annotation,
     * which requires the user to have the `ROLE_MANAGER` role.
     * 
     * @param user user the authenticated user
     * @return a list of {@link User} objects if the authenticated user is a
     * manager; an empty list otherwise
     */
    @Secured("ROLE_MANAGER")
    @RequestMapping(value="/api/v1/all-users", method=RequestMethod.GET)
    public List<User> getUsers(@AuthenticationPrincipal SecurityUser user) {
        // play it safe and return an empty list if the user is not a manager
        if (!user.getUser().isManager()) {
            return new LinkedList<>();
        }

        // return all users
        return userService.getUsers();
    }

    /**
     * Get the current user. This method is secured by the {@link Secured}
     * annotation, which requires the user to have the `ROLE_USER` role.
     * 
     * @param user the authenticated user
     * @return a {@link Map} containing the user's name, username, and whether
     * or not they are a manager
     */
    @Secured("ROLE_USER")
    @RequestMapping(value="/api/v1/users", method=RequestMethod.GET)
    public Map<String, Object> getUser(@AuthenticationPrincipal SecurityUser user) {
        // create a map to store the response
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", user.getUser().getName());
        map.put("username", user.getUsername());
        map.put("isManager", user.getUser().isManager());

        // return the map
        return map;
    }

    /**
     * Create a new user if the specified username is available.
     * 
     * @return Map with either an error or success message
     */
    @RequestMapping(value="/api/v1/users", method=RequestMethod.POST)
    public Map<String, Object> createUser(@RequestBody Map<String, Object> payload) {
        // create a map to store the response
        Map<String, Object> map = new HashMap<String, Object>();

        // get the name, username, and password from the request body
        String name = (String) payload.get("name");
        String username = (String) payload.get("username");
        String password = (String) payload.get("password");

        // check if any of the parameters are missing
        if (name == null || username == null || password == null) {
            map.put("error", "Invalid userid or password or name");
            return map;
        }

        if (password.length() < 1) {
            map.put("error", "Invalid userid or password or name");
            return map;
        }

        if (name.length() < 1) {
            map.put("error", "Invalid userid or password or name");
            return map;
        }

        if (username.length() < 1) {
            map.put("error", "Invalid userid or password or name");
            return map;
        }

        // check if the username is already taken
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            map.put("error", "Invalid userid or passwordn or name");
            return map;
        }

        // create a new user and save it to the database
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User newUser = new User(username, passwordEncoder.encode(password), name, false);
        userRepository.save(newUser);

        // return a success message
        map.put("success", "User created");
        return map;
    }

    /**
     * Set the manager status of a user.
     * 
     * @param payload a {@link Map} containing the name of the field to update
     * and the username of the user to update. Currently only the `isManager`
     * field is supported. The method is secured by the {@link Secured}
     * annotation, which requires the user to have the `ROLE_MANAGER` role.
     */
    @Secured("ROLE_MANAGER")
    @RequestMapping(value="/api/v1/users", method=RequestMethod.PUT)
    public Map<String, Object> setManager(@RequestBody Map<String, Object> payload) {
        // create a map to store the response
        Map<String, Object> map = new HashMap<String, Object>();

        // get the field and username from the request body
        String field = (String) payload.get("field");
        String username = (String) payload.get("username");

        // check if any of the parameters are missing
        if (field == null || username == null) {
            map.put("error", "Missing parameters");
            return map;
        }

        // check if the field is valid
        if (!field.equals("isManager")) {
            map.put("error", "Invalid field");
            return map;
        }

        // check if the user exists
        Optional<User> user = userService.findByUsername(username);
        if (user.isEmpty()) {
            map.put("error", "User does not exist");
            return map;
        }

        // update the user and save it to the database
        user.get().setManager(true);
        userRepository.save(user.get());

        // return a success message
        map.put("success", "User is now a manager");
        return map;
    }

    /**
     * Check if the user is currently logged in.
     * 
     * @param user the authenticated user
     * @return a {@link Map} containing a boolean value indicating whether or
     * not the user is logged in
     */
    @RequestMapping(value="/api/v1/user/loggedin", method=RequestMethod.POST)
    public Map<String, Object> isLoggedIn(@AuthenticationPrincipal SecurityUser user) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (user == null) {
            map.put("loggedin", false);
            return map;
        }

        map.put("loggedin", true);
        return map;
    }
}