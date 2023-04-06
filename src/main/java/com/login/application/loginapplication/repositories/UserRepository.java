package com.login.application.loginapplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.application.loginapplication.models.User;

/**
 * The Interface UserRepository. This interface is used to create a repository
 * for the User object. This repository is used to access the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
