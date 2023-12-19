package com.example.cinematicaDemo.models.dto;

import com.example.cinematicaDemo.enums.PriceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceDto extends WorkDateDto {

    Long id;
    Double price;
    PriceType priceType;

}
