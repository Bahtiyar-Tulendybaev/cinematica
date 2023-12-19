package com.example.cinematicaDemo.service;

import com.example.cinematicaDemo.enums.PriceType;
import com.example.cinematicaDemo.models.dto.PriceDto;
import com.example.cinematicaDemo.models.request.SavePriceRequest;

import java.util.List;

public interface PriceService extends BaseService<PriceDto>{
    PriceDto create(SavePriceRequest price);
    Double getPrice(PriceType priceType);
    List<PriceDto> findPrice(PriceType priceType);
}
