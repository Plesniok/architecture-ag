package com.project.products.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse <T> {

    private String status;

    private Integer httpStatus;

    private String message;

    private String internalCode;

    private T data;

}
