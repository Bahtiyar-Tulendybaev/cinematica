package com.example.cinematicaDemo.dao;

import com.example.cinematicaDemo.enums.PriceType;
import com.example.cinematicaDemo.models.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PriceRep extends JpaRepository<Price,Long> {

    @Query("Select p from Price as p\n" +
            "WHERE p.priceType= :priceType")
    List<Price> findPrice(PriceType priceType);
}
