package com.example.cinematicaDemo.models.responses;

import com.example.cinematicaDemo.enums.PriceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {

    Long orderId;
    String movie;
    String cinema;
    String room;
    int row;
    int seatNumber;
    PriceType priceType;
    LocalDate startDay;
    LocalTime startTime;
    double price;

}
