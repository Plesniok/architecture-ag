package com.project.products.models.api;

public class SimpleResponse {
    private final int statusCode;
    private final String responseData;

    public SimpleResponse(int statusCode, String responseData) {
        this.statusCode = statusCode;
        this.responseData = responseData;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseData() {
        return responseData;
    }
}
