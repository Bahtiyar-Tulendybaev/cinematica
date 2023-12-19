package com.example.cinematicaDemo.mappers;

import com.example.cinematicaDemo.models.dto.RoomMovieDto;
import com.example.cinematicaDemo.models.entities.RoomMovie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface RoomMovieMapper extends BaseMapper<RoomMovie, RoomMovieDto>{
    RoomMovieMapper INSTANCE = Mappers.getMapper(RoomMovieMapper.class);

}
