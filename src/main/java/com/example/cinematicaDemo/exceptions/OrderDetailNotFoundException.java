package com.example.cinematicaDemo.exceptions;

public class OrderDetailNotFoundException extends RuntimeException{
    public OrderDetailNotFoundException(String message) {
        super(message);
    }
}
