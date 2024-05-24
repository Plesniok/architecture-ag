package com.project.products.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "categories")
public class Category {
    /**
     * Entity error mapping rules.
     *
     * Value of invalid argument error should equal one of keys number part in Constant.getDetectionResponseHashMap.
     * Constant class is located in com.project.products.models.Constant
     */

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    @NotNull(message = "MESSAGEID")
    private String name;

    public @NotNull(message = "MESSAGEID") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "MESSAGEID") String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}