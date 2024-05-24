package com.project.products.validation;

import com.project.products.models.api.SimpleResponse;

public class ResponseError extends Exception{
    final private SimpleResponse response;

    public ResponseError(SimpleResponse response) {
        super(response.getResponseData());

        this.response = response;
    }

    @Override
    public String toString() {
        return String.format("Request failed with code: %d and message: %s",  response.getStatusCode(), response.getResponseData());
    }

    public SimpleResponse getResponse() {
        return response;
    }
}
