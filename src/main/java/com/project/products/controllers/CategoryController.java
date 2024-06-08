package com.project.products.controllers;

import com.project.products.database.CategoryRepository;
import com.project.products.exceptions.NotFoundException;
import com.project.products.models.Category;
import com.project.products.models.api.Constant;
import com.project.products.models.api.ApiResponse;
import com.project.products.models.api.Responses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("category")
    public ResponseEntity<ApiResponse<Category>> addCategory(@RequestBody @Valid Category newCategoryData) {

        Category savedCategory = categoryRepository.save(newCategoryData);

        return Responses.ok(savedCategory, Constant.getProductsResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

    @GetMapping("categories")
    public ResponseEntity<ApiResponse<List<Category>>> getCategories() {

        List<Category> allCategories = categoryRepository.findAll();


        return Responses.ok(allCategories, Constant.getProductsResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

    @GetMapping("category/{categoryId}")
    public ResponseEntity<ApiResponse<Category>> getCategory(@PathVariable Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("3"));

        return Responses.ok(category, Constant.getProductsResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

}
