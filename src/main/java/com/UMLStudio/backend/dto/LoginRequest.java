package com.UMLStudio.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    // One of username or email must be provided
    private String username;

    private String email;

    @NotBlank
    private String password;

    public LoginRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

