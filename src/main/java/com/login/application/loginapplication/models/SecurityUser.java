package com.login.application.loginapplication.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The SecurityUser class implements the @{link UserDetails} interface and is
 * used to create a @{link UserDetails} object from a @{link User} object. This
 * is used by Spring Security to authenticate users.
 * 
 * {@link UserDetails} is an interface that represents a user's information
 * that is used by Spring Security to authenticate users.
 * 
 * @see org.springframework.security.core.userdetails.UserDetails
 * 
 * @author Alex Koh
 */
public class SecurityUser implements UserDetails {
    /**
     * The user.
     */
    private final User user;

    /**
     * Instantiates a new security user.
     * 
     * @param user the user
     */
    public SecurityUser(User user) {
        this.user = user;
    }

    /**
     * Gets the authorities. If the user is a manager, they are granted the
     * `ROLE_MANAGER` and `ROLE_USER` roles. Otherwise, they are granted the
     * `ROLE_USER` role.
     * 
     * @return the authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.isManager()
            ? List.of(new SimpleGrantedAuthority("ROLE_MANAGER"), new SimpleGrantedAuthority("ROLE_USER"))
            : List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Gets the username.
     * 
     * @return the username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Gets the password.
     * 
     * @return the password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Checks if is account non expired.
     * 
     * @return true, if is account non expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if is account non locked.
     * 
     * @return true, if is account non locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Checks if is credentials non expired.
     * 
     * @return true, if is credentials non expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if is enabled.
     * 
     * @return true, if is enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Gets the user.
     * 
     * @return the user
     */
    public User getUser() {
        return user;
    }
}
