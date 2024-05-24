package com.project.products.models.api;

import com.project.products.models.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class Responses {
    /**
     * Creates an APIResponse operation.
     *
     * @param data             The data to include in the response.
     * @param responseHashMap  A map containing response messages.
     * @param key              The key corresponding to the desired response message.
     * @param <T>              The type of data to be included in the response.
     * @return An APIResponse indicating a successful operation.
     */
    public static <T> ResponseEntity<ApiResponse<T>> ok(T data, Map<String, String> responseHashMap, String key) {
        ApiResponse responseObject = ApiResponse.<T>builder()
                .httpStatus(HttpStatus.OK.value())
                .status(Constant.RESULT_OK)
                .message(responseHashMap.get(key))
                .internalCode(key)
                .data(data)
                .build();
        return ResponseEntity.status(HttpStatus.OK.value()).contentType(MediaType.APPLICATION_JSON).body(responseObject);
    }

    public static <T> ResponseEntity<ApiResponse<T>> conflict(T data, Map<String, String> responseHashMap, String key) {
        ApiResponse responseObject = ApiResponse.<T>builder()
                .httpStatus(HttpStatus.CONFLICT.value())
                .status(Constant.RESULT_CONFLICT)
                .message(responseHashMap.get(key))
                .internalCode(key)
                .data(data)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT.value()).contentType(MediaType.APPLICATION_JSON).body(responseObject);
    }

    public static <T> ResponseEntity<ApiResponse<T>> internalServerError(T data, Map<String, String> responseHashMap, String key) {
        ApiResponse responseObject = ApiResponse.<T>builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(Constant.RESULT_INTERNAL_SERVER_ERROR)
                .message(responseHashMap.get(key))
                .internalCode(key)
                .data(data)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).contentType(MediaType.APPLICATION_JSON).body(responseObject);
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(T data, Map<String, String> responseHashMap, String key) {
        ApiResponse responseObject = ApiResponse.<T>builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .status(Constant.RESULT_BAD_REQUEST)
                .message(responseHashMap.get(key))
                .internalCode(key)
                .data(data)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).contentType(MediaType.APPLICATION_JSON).body(responseObject);
    }

    public static <T> ResponseEntity<ApiResponse<T>> notFound(T data, Map<String, String> responseHashMap, String key) {
        ApiResponse responseObject = ApiResponse.<T>builder()
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .status(Constant.RESULT_NOT_FOUND)
                .message(responseHashMap.get(key))
                .internalCode(key)
                .data(data)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).contentType(MediaType.APPLICATION_JSON).body(responseObject);
    }

    public static <T> ResponseEntity<ApiResponse<T>> any(T data, Map<String, String> responseHashMap, String key, int statusCode) {
        ApiResponse responseObject = ApiResponse.<T>builder()
                .httpStatus(statusCode)
                .status(Constant.RESULT_NOT_FOUND)
                .message(responseHashMap.get(key))
                .internalCode(key)
                .data(data)
                .build();

        return ResponseEntity.status(statusCode).contentType(MediaType.APPLICATION_JSON).body(responseObject);
    }

}

