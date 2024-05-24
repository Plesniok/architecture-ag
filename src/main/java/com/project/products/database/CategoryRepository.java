package com.project.products.database;

import com.project.products.models.Category;
import com.project.products.models.Price;
import com.project.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByName(String name);

}
