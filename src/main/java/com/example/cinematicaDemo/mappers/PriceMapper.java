package com.example.cinematicaDemo.mappers;

import com.example.cinematicaDemo.models.dto.PriceDto;
import com.example.cinematicaDemo.models.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PriceMapper extends BaseMapper<Price, PriceDto>{
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
}
