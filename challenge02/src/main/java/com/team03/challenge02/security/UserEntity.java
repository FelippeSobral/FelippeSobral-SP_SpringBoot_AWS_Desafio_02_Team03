package com.team03.challenge02.security;

import org.springframework.security.core.GrantedAuthority;

public interface UserEntity {
    String getEmail();
    String getPassword();
    GrantedAuthority getRole();
}
