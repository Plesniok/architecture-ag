package com.project.products.controllers;

import com.project.products.database.UsersRepository;
import com.project.products.exceptions.NotFoundException;
import com.project.products.models.api.Constant;
import com.project.products.models.User;
import com.project.products.models.api.ApiResponse;
import com.project.products.models.api.Responses;
import com.project.products.models.api.requests.LoginRequest;
import com.project.products.models.api.responses.LoginResponse;
import com.project.products.services.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("user")
    public ResponseEntity<ApiResponse<User>> addUser(@RequestBody @Valid User newUserData) {

        usersRepository.save(newUserData);

        return Responses.ok(newUserData, Constant.getProductsResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

    @GetMapping("user/role")
    public ResponseEntity<ApiResponse<String>> getUserRole(
            @RequestHeader("X-access-token") String jwt
    ) {

        Claims decodedToken = JwtService.decodeTokenToPayload(jwt);

        return Responses.ok((String) decodedToken.get("role"), Constant.getProductsResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

    @PutMapping("login")
    public ResponseEntity<ApiResponse<LoginResponse>> authenticate(@RequestBody @Valid LoginRequest loginRequest) {

        User foundUserData = usersRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
                .orElseThrow(() -> new NotFoundException("3"));

        String newToken = JwtService.generateNewToken(
                foundUserData.getEmail(),
                foundUserData.getRole(),
                foundUserData.getId()
        );

        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setToken(newToken);

        return Responses.ok(loginResponse, Constant.getProductsResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

}
