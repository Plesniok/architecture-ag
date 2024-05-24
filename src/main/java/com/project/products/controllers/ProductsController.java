package com.project.products.controllers;

import com.project.products.database.CategoryRepository;
import com.project.products.database.PricesRepository;
import com.project.products.database.ProductsRepository;
import com.project.products.exceptions.NotFoundException;
import com.project.products.models.*;
import com.project.products.models.api.ApiResponse;
import com.project.products.models.api.Responses;
import com.project.products.services.UpcService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    public ProductsController(
            ProductsRepository productsRepository,
            CategoryRepository categoryRepository,
            PricesRepository pricesRepository
    ) {
        this.productsRepository = productsRepository;
        this.categoryRepository = categoryRepository;
        this.pricesRepository = pricesRepository;
    }

    private final ProductsRepository productsRepository;
    private final PricesRepository pricesRepository;
    private final CategoryRepository categoryRepository;

    @PostMapping("/product")
    public ResponseEntity<ApiResponse<Product>> addProduct(@RequestBody @Valid Product newProductData) {

        categoryRepository.findById((long) newProductData.getCategoryId()).orElseThrow(() -> new NotFoundException("4"));

        final String newProductUpcCode = UpcService.generateRandomUPC();

        newProductData.setUpc(newProductUpcCode);

        productsRepository.save(newProductData);


        return Responses.ok(newProductData, Constant.getDetectionResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable Long productId) {

        Product foundProduct = productsRepository.findById(productId).orElseThrow(() -> new HttpMessageNotReadableException("3"));


        return Responses.ok(foundProduct, Constant.getDetectionResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

    @GetMapping("/product/{productId}/label")
    public ResponseEntity<ApiResponse<ProductLabel>> getProductLabel(@PathVariable Long productId) {

        Product foundProduct = productsRepository.findById(productId).orElseThrow(() -> new HttpMessageNotReadableException("3"));

        ProductLabel productLabel = foundProduct.castToProductLabel();

        List<Price> productLatestPrices = pricesRepository.findByProductIdAndValidToIsNull(foundProduct.getId());

        if(!productLatestPrices.isEmpty()){
            productLabel.setPrice(productLatestPrices.getFirst());

        }

        Category foundCategory = categoryRepository.findById(Long.valueOf(foundProduct.getCategoryId())).orElseThrow(() -> new HttpMessageNotReadableException("3"));

        productLabel.setCategory(foundCategory);

        return Responses.ok(productLabel, Constant.getDetectionResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }
}
