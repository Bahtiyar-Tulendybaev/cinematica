package com.example.cinematicaDemo.dao;

import com.example.cinematicaDemo.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRep extends JpaRepository<Order, Long> {
}
