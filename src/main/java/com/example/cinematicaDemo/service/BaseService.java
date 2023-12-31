package com.example.cinematicaDemo.service;

import java.util.List;

public interface BaseService<Z> {
    Z save(Z t);
    Z findById(Long id);
    Z delete(Long id);
    List<Z> findAll();


}
