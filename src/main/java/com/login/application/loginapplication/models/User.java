package com.login.application.loginapplication.models;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class User. This class is used to create a User object. This object is
 * used to store user information in the database.
 */
@Getter
@Setter
@Entity
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
    })
public class User {
    /**
     * The id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username.
     */
    @NaturalId
    private String username;

    /**
     * The name.
     */
    private String name;

    /**
     * The password.
     */
    @NotBlank
    private String password;

    /**
     * Returns true if the user is a manager.
     */
    private boolean isManager;

    /**
     * Instantiates a new user.
     */
    public User() {}

    /**
     * Instantiates a new user.
     * @param username the username
     * @param password the password
     * @param name the name
     * @param isManager true if the user is a manager
     */
    public User(String username, String password, String name, Boolean isManager) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.isManager = isManager;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        return "User: {" +
            "id: " + id + ", " +
            "username: " + username + ", " +
            "name: " + name + ", " +
            "isManager: " + isManager +"}";
    }
}
