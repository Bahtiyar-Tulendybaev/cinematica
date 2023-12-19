package com.example.cinematicaDemo.exceptions;

public class SeatScheduleNotFoundException extends RuntimeException{
    public SeatScheduleNotFoundException(String message) {
        super(message);
    }
}
