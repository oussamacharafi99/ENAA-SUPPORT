package com.ENAA_SUPPORT.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_TECHNICIAN;

    @Override
    public String getAuthority() {
        return name();
    }
}
