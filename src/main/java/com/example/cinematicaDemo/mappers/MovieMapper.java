package com.example.cinematicaDemo.mappers;

import com.example.cinematicaDemo.models.dto.MovieDto;
import com.example.cinematicaDemo.models.entities.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper extends BaseMapper<Movie, MovieDto> {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);


}
