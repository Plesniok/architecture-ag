package com.project.products.controllers;

import com.project.products.database.UsersRepository;
import com.project.products.exceptions.NotFoundException;
import com.project.products.models.Constant;
import com.project.products.models.User;
import com.project.products.models.api.ApiResponse;
import com.project.products.models.api.Responses;
import com.project.products.models.requests.LoginRequest;
import com.project.products.models.validationGroups.UserUpdate;
import com.project.products.services.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("user")
    public ResponseEntity<ApiResponse<User>> addUser(@RequestBody @Valid User newUserData) {

        usersRepository.save(newUserData);

        return Responses.ok(newUserData, Constant.getDetectionResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

    @PutMapping("login")
    public ResponseEntity<ApiResponse<User>> addUser(@RequestBody @Valid LoginRequest loginRequest) {

        User foundUserData = usersRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
                .orElseThrow(() -> new NotFoundException("3"));

        String newToken = JwtService.generateNewToken(
                foundUserData.getEmail(),
                foundUserData.getRole(),
                foundUserData.getId()
        );

        foundUserData.setToken(newToken);

        String newRefreshToken = JwtService.generateNewToken(
                foundUserData.getEmail(),
                foundUserData.getRole(),
                foundUserData.getId()
        );

        foundUserData.setRefreshToken(newRefreshToken);

        usersRepository.save(foundUserData);

        return Responses.ok(foundUserData, Constant.getDetectionResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<User>> editUser(
            @PathVariable Long userId,
            @RequestBody @Valid User newUserData,
            @RequestHeader("X-access-token") String jwt
    ) {

        Claims decodedToken = JwtService.decodeTokenToPayload(jwt);

        int tokenUserId = (Integer) decodedToken.get("id");

        if(!((long) tokenUserId == userId)){
            return Responses.conflict(null, Constant.getDetectionResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("1"));
        }

        newUserData.setId(tokenUserId);
        usersRepository.save(newUserData);

        return Responses.ok(newUserData, Constant.getDetectionResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

}
