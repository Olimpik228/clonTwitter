package com.example.cloneTwitter.domain;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public enum Role implements GrantedAuthority, Serializable {
    USER, ADMIN;;

    @Override
    public String getAuthority() {
        return name();
    }
}
