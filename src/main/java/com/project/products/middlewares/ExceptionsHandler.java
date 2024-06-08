package com.project.products.middlewares;

import com.project.products.exceptions.NotFoundException;
import com.project.products.models.api.Constant;
import com.project.products.models.api.ApiResponse;
import com.project.products.models.api.Responses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Null;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ApiResponse<Object>> notFoundError(NotFoundException ex) {
        return Responses.notFound(null, Constant.getProductsResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat(ex.getMessage()));

    }

    @ExceptionHandler({JdbcSQLIntegrityConstraintViolationException.class})
    public ResponseEntity<ApiResponse<Object>> notFoundError(JdbcSQLIntegrityConstraintViolationException ex) {
        return Responses.conflict(null, Constant.getProductsResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("5"));

    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse<Null>> notValid(MethodArgumentNotValidException e, HttpServletRequest ignoredRequest) {

        String invalidParametersErrorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toSet())
                .toString()
                .replaceAll("\\[*]*", "");

        return Responses.badRequest(null, Constant.getProductsResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat(invalidParametersErrorMessage));

    }

    // You can add more exception handlers for other types of exceptions if needed
}


