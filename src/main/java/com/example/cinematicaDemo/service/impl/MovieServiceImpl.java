package com.example.cinematicaDemo.service.impl;

import com.example.cinematicaDemo.exceptions.MovieNotFoundException;
import com.example.cinematicaDemo.service.MovieService;
import com.example.cinematicaDemo.dao.MovieRep;
import com.example.cinematicaDemo.mappers.MovieMapper;
import com.example.cinematicaDemo.models.dto.MovieDto;
import com.example.cinematicaDemo.models.request.SaveMovieRequest;
import com.example.cinematicaDemo.models.responses.GetAllMoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    MovieMapper mapper = MovieMapper.INSTANCE;

    private final MovieRep rep;
    @Autowired
    public MovieServiceImpl(MovieRep rep) {
        this.rep = rep;
    }

    @Override
    public MovieDto save(MovieDto movieDto) {
        return mapper.toDto(rep.save(mapper.toEntity(movieDto)));
    }

    @Override
    public MovieDto create(SaveMovieRequest movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setName(movie.getName());
        movieDto.setDefinition(movie.getDefinition());
        movieDto.setRating(movie.getRating());
        movieDto.setPg(movie.getPg());
        movieDto.setImage(movie.getImage());

        return save(movieDto);
    }

    @Override
    public MovieDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie not found!")));
    }

    @Override
    public MovieDto delete(Long id) {
        MovieDto movieDto = findById(id);
        movieDto.setActive(false);
        return save(movieDto);
    }


    @Override
    public List<MovieDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public List<MovieDto> findAllMovies(int limit, int offset) {
        return mapper.toDtos(rep.findAllMovies(limit,offset));
    }

    @Override
    public List<GetAllMoviesResponse> getAllMovies(int limit, int offset) {
        List<MovieDto> movieList = findAllMovies(limit,offset);
        List<GetAllMoviesResponse> getAllMoviesResponseList = new ArrayList<>();

        for (MovieDto item:movieList){
            GetAllMoviesResponse getAllMoviesResponse= new GetAllMoviesResponse();
            getAllMoviesResponse.setId(item.getId());
            getAllMoviesResponse.setName(item.getName());
            getAllMoviesResponse.setRating(item.getRating());
            getAllMoviesResponse.setPg(item.getPg());
            getAllMoviesResponse.setImageLink(item.getImage());
            getAllMoviesResponseList.add(getAllMoviesResponse);
        }
        return getAllMoviesResponseList ;
    }
}
