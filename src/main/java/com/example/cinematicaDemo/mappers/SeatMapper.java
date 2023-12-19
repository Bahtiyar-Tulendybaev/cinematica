package com.example.cinematicaDemo.mappers;

import com.example.cinematicaDemo.models.dto.SeatDto;
import com.example.cinematicaDemo.models.entities.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface SeatMapper extends BaseMapper<Seat, SeatDto>{
    SeatMapper INSTANCE = Mappers.getMapper(SeatMapper.class);

}
