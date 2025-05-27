package com.example.forum.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class FirebaseUserDetails implements UserDetails {

    private final String uid;
    private final String email;
    private final String displayName;
    private final boolean emailVerified;
    private final boolean active;
    private final Collection<? extends GrantedAuthority> authorities;

    public FirebaseUserDetails(String uid, String email, String displayName, boolean emailVerified, boolean active, Collection<? extends GrantedAuthority> authorities) {
        this.uid = uid;
        this.email = email;
        this.displayName = displayName;
        this.emailVerified = emailVerified;
        this.active = active;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null; // Not used for Firebase token-based auth
    }

    @Override
    public String getUsername() {
        return uid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return emailVerified && active;
    }
}