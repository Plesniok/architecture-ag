package com.project.products.services;

import com.project.products.database.PricesRepository;
import com.project.products.database.ProductsRepository;
import com.project.products.models.Price;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DateService {

    public DateService(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Autowired
    private PricesRepository pricesRepository;

    public static Date getMonthAgoFromNow(){
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.add(Calendar.MONTH, -1);

        return calendar.getTime();
    }

    @Transactional
    public void setLastPriceValidOnNow(Price newPrice){


        Date now = new Date();

        Long productId = (long) newPrice.getProductId();

        List<Price> existingPrices = pricesRepository.findByProductIdAndValidToIsNull(productId);
        for (Price existingPrice : existingPrices) {
            existingPrice.setValidTo(now);
            pricesRepository.save(existingPrice);
        }

        pricesRepository.save(newPrice);

    }
}
