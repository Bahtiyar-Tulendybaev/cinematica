package com.example.cinematicaDemo.service;

import com.example.cinematicaDemo.models.dto.MovieDto;
import com.example.cinematicaDemo.models.request.SaveMovieRequest;
import com.example.cinematicaDemo.models.responses.GetAllMoviesResponse;

import java.util.List;

public interface MovieService extends BaseService<MovieDto>{
    MovieDto create(SaveMovieRequest movie);

    List<MovieDto> findAllMovies(int limit, int offset);
    List<GetAllMoviesResponse> getAllMovies(int limit, int offset);
}
