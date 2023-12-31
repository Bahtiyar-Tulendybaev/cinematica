package com.example.cinematicaDemo.service;

import com.example.cinematicaDemo.models.dto.RoomMoviePriceDto;
import com.example.cinematicaDemo.models.responses.*;


import java.time.LocalDate;
import java.util.List;

public interface RoomMoviePriceService extends BaseService<RoomMoviePriceDto>{

    Response create(Long roomMovieId, List<Long> priceList);
    List<RoomMoviePriceDto> findPriceByMovieId(Long movieId,LocalDate startDate);
    GetRoomMovieResponse getRoomMovieByMovieId(Long movieId, LocalDate startDate);

    List<RoomMoviePriceDto> findPriceByCinemaId(Long cinemaId,LocalDate startDate);
    GetRoomMovieByCinemaResponse getRoomMovieByCinemaId(Long cinemaId, LocalDate startDate);
    List<RoomMovieResponse> getRoomMovieResponse(Long cinemaId, LocalDate startDate);
    List<MovieResponse> getMovieResponse(Long cinemaId, LocalDate startDate);
    List<RoomResp> getRoomResp(Long cinemaId, LocalDate startDate);


}
