package com.example.cinematicaDemo.service;

import com.example.cinematicaDemo.models.dto.RoomMovieDto;
import com.example.cinematicaDemo.models.request.SaveRoomMovieRequest;

import java.time.LocalDate;
import java.util.List;

public interface RoomMovieService extends BaseService<RoomMovieDto>{
    RoomMovieDto create(SaveRoomMovieRequest roomMovie);

    List<RoomMovieDto> getAllByMovieId(Long id);

    List<RoomMovieDto> findRoomMovieByMovieId(Long movieId, LocalDate startDate);
    List<RoomMovieDto> findRoomMovieByCinemaId(Long cinemaId, LocalDate startDate);
}
