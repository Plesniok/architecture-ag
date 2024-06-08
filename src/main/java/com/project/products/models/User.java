package com.project.products.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;



@Entity
@Table(name = "users")
public class User {
    /**
     * Entity error mapping rules.
     *
     * Value of invalid argument error should equal one of keys number part in Constant.getDetectionResponseHashMap.
     * Constant class is located in com.project.products.models.api.Constant
     */

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int id;

    @NotNull()
    @Column(unique = true)
    private String email;

    @NotNull()
    private String password;

    @NotNull()
    @Column(updatable = false)
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
