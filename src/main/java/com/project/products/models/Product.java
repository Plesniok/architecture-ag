package com.project.products.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String upc;

    @NotNull(message = "ERRORID")
    @Column(unique = true)
    private String name;

    @NotNull(message = "MESSAGEID")
    private int categoryId;

    public long getId() {
        return id;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull(message = "ERRORID") String name) {
        this.name = name;
    }

    @NotNull(message = "MESSAGEID")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NotNull(message = "MESSAGEID") int categoryId) {
        this.categoryId = categoryId;
    }

    public ProductLabel castToProductLabel(){
        return new ProductLabel(id, upc, name);
    }
}
