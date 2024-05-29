package com.project.products.config;

import com.project.products.services.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/user").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/user/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/product/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/product/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/products/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/price/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/prices/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/category/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/categories/**").hasAnyAuthority("ADMIN", "USER")
                        .anyRequest().permitAll()
        );

        http.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}