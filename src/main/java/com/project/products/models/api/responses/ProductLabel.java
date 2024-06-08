package com.project.products.models.api.responses;

import com.project.products.models.Category;
import com.project.products.models.Price;

import java.util.Date;

public class ProductLabel {

    private final int id;

    private final String upc;

    private final String name;

    private Category category;

    private Price price;

    public ProductLabel (int id, String upc, String name){
        this.id = id;
        this.upc = upc;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getUpc() {
        return upc;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

}
