package com.project.products.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;

import java.util.Date;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

public class JwtService {

    static public String generateNewToken(String email, String role, long id){
        SecretKey key = Jwts.SIG.HS256.key().build();


        String base64Key = "T5le/sNF/ltzky0S6xrbdCzyy9IBoPn9EaCZiEo2u0RKNEapxjJBrJT25F6Z6UliLfUJ7AtX8MZAA7VEA+ONlA==";

        Date currentTime = new Date();

        Date expirationDate = new Date(currentTime.getTime() + (45 * 60 * 1000));

        return Jwts.builder()
                .claim("email", email)
                .claim("role", role)
                .claim("id", id)
                .setExpiration(expirationDate)
                .signWith(
                        SignatureAlgorithm.HS512, base64Key
                )
                .compact();
    }


    static public String generateNewRefreshToken(String email, String role, long id){
        SecretKey key = Jwts.SIG.HS256.key().build();


        String base64Key = "T5le/sNF/ltzky0S6xrbdCzyy9IBoPn9EaCZiEo2u0RKNEapxjJBrJT25F6Z6UliLfUJ7AtX8MZAA7VEA+ONlA==";

        Date currentTime = new Date();

        Date expirationDate = new Date(currentTime.getTime() + (45 * 60 * 1000));

        return Jwts.builder()
                .claim("email", email)
                .claim("role", role)
                .claim("id", id)
                .setExpiration(expirationDate)
                .signWith(
                        SignatureAlgorithm.HS512, base64Key
                )
                .compact();
    }

    static public Claims decodeTokenToPayload(String token) {
        try{

            String base64Key = "T5le/sNF/ltzky0S6xrbdCzyy9IBoPn9EaCZiEo2u0RKNEapxjJBrJT25F6Z6UliLfUJ7AtX8MZAA7VEA+ONlA==";

            return Jwts.parser().setSigningKey(base64Key).build().parseSignedClaims(token).getPayload();
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            System.out.println("Invalid token");
            return null;
        }

    }


}
