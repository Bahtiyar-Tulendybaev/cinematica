package com.example.cinematicaDemo.models.request;

import com.example.cinematicaDemo.enums.PriceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SavePriceRequest {
    Double price;
    PriceType priceType;
}
