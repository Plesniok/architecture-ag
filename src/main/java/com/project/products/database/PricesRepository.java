package com.project.products.database;

import com.project.products.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PricesRepository extends JpaRepository<Price, Long> {
    List<Price> findByProductIdAndValidFromAfter(Long productId, Date validFrom);

    List<Price> findByProductIdAndValidToIsNull(Long productId);
}

