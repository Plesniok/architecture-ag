package com.project.products.controllers;

import com.project.products.database.PricesRepository;
import com.project.products.database.ProductsRepository;
import com.project.products.exceptions.NotFoundException;
import com.project.products.models.Constant;
import com.project.products.models.Price;
import com.project.products.models.Product;
import com.project.products.models.api.ApiResponse;
import com.project.products.models.api.Responses;
import com.project.products.services.DateService;
import jakarta.validation.Valid;
import jakarta.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class PricesController {
    @Autowired
    public PricesController(ProductsRepository productsRepository, PricesRepository pricesRepository, DateService dateService) {
        this.productsRepository = productsRepository;
        this.pricesRepository = pricesRepository;
        this.dateService = dateService;
    }

    private final ProductsRepository productsRepository;
    private final PricesRepository pricesRepository;
    private final DateService dateService;

    @PostMapping("price/{productId}")
    public ResponseEntity<ApiResponse<Price>> addPriceToProduct(@PathVariable int productId, @RequestBody @Valid Price newPrice) {

        productsRepository.findById((long) productId).orElseThrow(() -> new NotFoundException("3"));

        newPrice.setProductId(productId);
        newPrice.setValidFrom(new Date(System.currentTimeMillis()));

        dateService.setLastPriceValidOnNow(newPrice);

        return Responses.ok(newPrice, Constant.getDetectionResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

    @GetMapping("prices/{productId}")
    public ResponseEntity<ApiResponse<List<Price>>> getProductPrices(@PathVariable Long productId) {

        Date validFrom = DateService.getMonthAgoFromNow();

        List<Price> prices = pricesRepository.findByProductIdAndValidFromAfter(productId, validFrom);

        return Responses.ok(prices, Constant.getDetectionResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

}
