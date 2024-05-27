package com.project.products.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response,
            jakarta.servlet.FilterChain filterChain
    ) throws jakarta.servlet.ServletException, IOException {

        final String jwtHeader = request.getHeader("Authorization");

        final Claims decodedJwt = JwtService.decodeTokenToPayload(jwtHeader);

        if(decodedJwt == null){
            filterChain.doFilter(request, response);

            return;
        }


        List<SimpleGrantedAuthority> userRoles = new java.util.ArrayList<>(Collections.emptyList());

        final String userRole = (String) decodedJwt.get("role");

        userRoles.add(new SimpleGrantedAuthority(userRole));

        if (userRole.equals("ADMIN")) {
            userRoles.add(new SimpleGrantedAuthority("USER"));
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                decodedJwt.get("email"), null, userRoles
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
