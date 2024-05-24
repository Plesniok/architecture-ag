package com.project.products.controllers;

import com.project.products.database.CategoryRepository;
import com.project.products.models.Category;
import com.project.products.models.Constant;
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
    public ResponseEntity<ApiResponse<Category>> addPriceToProduct(@RequestBody @Valid Category newCategory) {

        List<Category> categoriesWithName = categoryRepository.findAllByName(newCategory.getName());

//        if (categoriesWithName){
//
//        }

        Category savedCategory = categoryRepository.save(newCategory);

        return Responses.ok(savedCategory, Constant.getDetectionResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

    @GetMapping("categories")
    public ResponseEntity<ApiResponse<List<Category>>> getProductPrices() {

        List<Category> allCategories = categoryRepository.findAll();


        return Responses.ok(allCategories, Constant.getDetectionResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

}
