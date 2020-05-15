package com.mozochek.utils;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    MODERATOR,
    ADMIN,
    DEVELOPER;

    @Override
    public String getAuthority() {
        return name();
    }
}
