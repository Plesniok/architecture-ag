package com.project.products.controllers;

import com.project.products.database.UsersRepository;
import com.project.products.models.Constant;
import com.project.products.models.User;
import com.project.products.models.api.ApiResponse;
import com.project.products.models.api.Responses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("user")
    public ResponseEntity<ApiResponse<User>> addUser(@RequestBody @Valid User newUserData) {

        newUserData.setPermissionsIds(Collections.emptyList());

        usersRepository.save(newUserData);

        return Responses.ok(newUserData, Constant.getDetectionResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

    @PutMapping("login")
    public ResponseEntity<ApiResponse<User>> addUser(@Bo) {

        usersRepository.findByEmailAndPassword()

        newUserData.setPermissionsIds(Collections.emptyList());

        usersRepository.save(newUserData);

        return Responses.ok(newUserData, Constant.getDetectionResponsesHashMap(), Constant.PRODUCTS_CODE_PREFIX.concat("2"));
    }

}
