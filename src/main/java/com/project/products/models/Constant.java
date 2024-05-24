package com.project.products.models;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constant {

    private Constant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String RESULT_OK = "OK";
    public static final String RESULT_BAD_REQUEST = "BAD REQUEST";
    public static final String RESULT_INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR";
    public static final String RESULT_CONFLICT = "CONFLICT";
    public static final String RESULT_NOT_FOUND = "NOT FOUND";

    public static final String PRODUCTS_CODE_PREFIX
 = "PRODUCTS-";

    final public static Map<String, String> getDetectionResponsesHashMap() {
        return Stream.of(
                Map.entry(PRODUCTS_CODE_PREFIX + "1", "Internal server error"),
                Map.entry(PRODUCTS_CODE_PREFIX + "2", "Success"),
                Map.entry(PRODUCTS_CODE_PREFIX + "3", "Product not found"),
                Map.entry(PRODUCTS_CODE_PREFIX + "4", "Category not found"),
                Map.entry(PRODUCTS_CODE_PREFIX + "5", "Unique values conflict"),
                Map.entry(PRODUCTS_CODE_PREFIX + "6", "Invalid category name"),
                Map.entry(PRODUCTS_CODE_PREFIX + "7", "Invalid product name"),
                Map.entry(PRODUCTS_CODE_PREFIX + "8", "Invalid category name"),
                Map.entry(PRODUCTS_CODE_PREFIX + "9", "Invalid product value"),
                Map.entry(PRODUCTS_CODE_PREFIX + "10", "category with given name already exists")

        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}