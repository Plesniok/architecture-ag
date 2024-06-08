package com.project.products.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    @NotNull(message = "6")
    private String name;

    public String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
