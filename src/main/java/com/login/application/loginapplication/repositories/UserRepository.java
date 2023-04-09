package com.login.application.loginapplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.application.loginapplication.models.User;

/**
 * The interface UserRepository.
 * 
 * This interface is used to create a repository for the @{link User} object.
 * This repository is used to access the database.
 * 
 * The {@code @Repository} annotation is used to specify that this interface is
 * a repository. A repository is used to access the database. The repository
 * is used to perform CRUD operations on the database. The repository is also
 * used to create custom queries.
 * 
 * The extended interface {@code JpaRepository} is used to specify the type of
 * object that the repository is for and the type of the primary key of the
 * object. The {@code JpaRepository} interface provides methods for performing
 * CRUD operations on the database.
 * 
 * @author Alex Koh
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by their username.
     * 
     * @param username the username
     * @return an optional User object with the matching username, or an empty 
     *         optional if not found
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user exists with the given username.
     * @param username the username
     * @return true if a user exists with the given username, false otherwise
     */
    Boolean existsByUsername(String username);
}
