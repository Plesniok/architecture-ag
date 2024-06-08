package com.project.products.models;

import com.project.products.models.api.responses.ProductLabel;
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

    @NotNull(message = "7")
    @Column(unique = true)
    private String name;

    @NotNull(message = "8")
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

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public ProductLabel castToProductLabel(){
        return new ProductLabel(id, upc, name);
    }
}
