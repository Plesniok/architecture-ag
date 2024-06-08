package com.project.products.models.api.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull()
    private String email;

    @NotNull()
    private String password;

    public @NotNull() String getEmail() {
        return email;
    }

    public @NotNull() String getPassword() {
        return password;
    }
}
