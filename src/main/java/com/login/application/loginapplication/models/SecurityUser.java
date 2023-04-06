package com.login.application.loginapplication.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The Class SecurityUser. This class is used to create a UserDetails object
 * from a User object.
 */
public class SecurityUser implements UserDetails {
    /**
     * The user.
     */
    private final User user;

    /**
     * Instantiates a new security user.
     * @param user the user
     */
    public SecurityUser(User user) {
        this.user = user;
    }

    /**
     * Gets the authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.isManager()
            ? List.of(new SimpleGrantedAuthority("ROLE_MANAGER"), new SimpleGrantedAuthority("ROLE_USER"))
            : List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Gets the username.
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Gets the password.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Checks if is account non expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if is account non locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Checks if is credentials non expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Gets the user.
     * @return
     */
    public User getUser() {
        return user;
    }
}
