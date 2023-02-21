package com.resellerapp.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserLoginSession {

    private Long id;

    private String username;

    public UserLoginSession() {}

    public void login(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public void logout() {
        this.id = null;
        this.username = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
