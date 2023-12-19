package com.example.cinematicaDemo.mappers;

import com.example.cinematicaDemo.models.dto.CinemaDto;
import com.example.cinematicaDemo.models.entities.Cinema;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CinemaMapper extends BaseMapper<Cinema, CinemaDto> {

    CinemaMapper INSTANCE = Mappers.getMapper(CinemaMapper.class);
}
