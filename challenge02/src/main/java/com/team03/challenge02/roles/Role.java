package com.team03.challenge02.roles;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

public enum Role implements GrantedAuthority {
    ROLE_TEACHER,
    ROLE_STUDENT,
    ROLE_COORDINATOR;


    @Override
    public String getAuthority() {
        return this.name();
    }
}
