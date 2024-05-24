package com.project.products.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UpcService {
    public static String generateRandomUPC() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 11; i++) { // Generate 11 random digits
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
