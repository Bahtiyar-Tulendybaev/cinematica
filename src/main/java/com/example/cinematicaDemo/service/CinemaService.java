package com.example.cinematicaDemo.service;

import com.example.cinematicaDemo.models.dto.CinemaDto;
import com.example.cinematicaDemo.models.request.SaveCinemaRequest;
import com.example.cinematicaDemo.models.responses.GetAllCinemasResponse;

import java.util.List;

public interface CinemaService extends BaseService<CinemaDto>{
    CinemaDto create(SaveCinemaRequest cinema);

    List<CinemaDto> findAllCinemas(int limit, int offset);
    List<GetAllCinemasResponse> getAllCinemas(int limit, int offset);
}
