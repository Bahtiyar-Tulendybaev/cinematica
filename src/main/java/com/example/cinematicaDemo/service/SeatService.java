package com.example.cinematicaDemo.service;

import com.example.cinematicaDemo.models.dto.SeatDto;
import com.example.cinematicaDemo.models.request.SaveSeatRequest;
import com.example.cinematicaDemo.models.responses.Response;

import java.util.List;

public interface SeatService extends BaseService<SeatDto>{
    Response create(SaveSeatRequest seat);

    List<SeatDto> findSeatsByRoomId(Long roomMovieId);
}
