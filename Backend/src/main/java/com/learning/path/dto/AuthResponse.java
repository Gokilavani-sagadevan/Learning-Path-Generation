package com.learning.path.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Add this
public class AuthResponse {
    private String token;
    private String username;
    private String role;

    // Add constructor manually
    public AuthResponse(String token, String username, String role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}