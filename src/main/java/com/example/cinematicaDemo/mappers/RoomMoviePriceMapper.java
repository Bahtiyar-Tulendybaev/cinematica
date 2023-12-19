package com.example.cinematicaDemo.mappers;

import com.example.cinematicaDemo.models.dto.RoomMoviePriceDto;
import com.example.cinematicaDemo.models.entities.RoomMoviePrice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMoviePriceMapper extends BaseMapper<RoomMoviePrice, RoomMoviePriceDto>{
    RoomMoviePriceMapper INSTANCE = Mappers.getMapper(RoomMoviePriceMapper.class);
}
