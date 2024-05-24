package com.project.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ProductsApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.project.products.services");
        context.scan("com.project.products.middlewares.validation");
        context.refresh();
    }

}
