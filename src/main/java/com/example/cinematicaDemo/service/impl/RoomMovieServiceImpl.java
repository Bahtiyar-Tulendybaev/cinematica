package com.example.cinematicaDemo.service.impl;

import com.example.cinematicaDemo.exceptions.RoomMovieNotFoundException;
import com.example.cinematicaDemo.service.RoomMovieService;
import com.example.cinematicaDemo.service.ScheduleService;
import com.example.cinematicaDemo.dao.RoomMovieRep;
import com.example.cinematicaDemo.mappers.RoomMovieMapper;
import com.example.cinematicaDemo.models.dto.MovieDto;
import com.example.cinematicaDemo.models.dto.RoomDto;
import com.example.cinematicaDemo.models.dto.RoomMovieDto;
import com.example.cinematicaDemo.models.dto.ScheduleDto;
import com.example.cinematicaDemo.models.request.SaveRoomMovieRequest;
import com.example.cinematicaDemo.service.MovieService;
import com.example.cinematicaDemo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomMovieServiceImpl implements RoomMovieService {
    RoomMovieMapper mapper = RoomMovieMapper.INSTANCE;


    private final RoomMovieRep rep;
    private final MovieService movieService;
    private final RoomService roomService;
    private final ScheduleService scheduleService;

    @Autowired
    public RoomMovieServiceImpl(RoomMovieRep rep, MovieService movieService,
                                RoomService roomService,
                                ScheduleService scheduleService) {
        this.rep = rep;
        this.movieService = movieService;
        this.roomService = roomService;
        this.scheduleService = scheduleService;
    }

    @Override
    public RoomMovieDto save(RoomMovieDto roomMovieDto) {
        return mapper.toDto(rep.save(mapper.toEntity(roomMovieDto)));
    }

    @Override
    public RoomMovieDto create(SaveRoomMovieRequest roomMovie) {

        MovieDto movieDto = movieService.findById(roomMovie.getMovieId());
        RoomDto roomDto = roomService.findById(roomMovie.getRoomId());
        ScheduleDto scheduleDto = scheduleService.findById(roomMovie.getScheduleId());
        RoomMovieDto roomMovieDto = new RoomMovieDto();
        roomMovieDto.setMovie(movieDto);
        roomMovieDto.setRoom(roomDto);
        roomMovieDto.setSchedule(scheduleDto);
        return save(roomMovieDto);
    }

    @Override
    public RoomMovieDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RoomMovieNotFoundException("RoomMovie not found!")));
    }

    @Override
    public RoomMovieDto delete(Long id) {
        RoomMovieDto roomMovieDto = findById(id);
        roomMovieDto.setActive(false);
        return save(roomMovieDto);
    }

    @Override
    public List<RoomMovieDto> getAllByMovieId(Long movieId) {
        return mapper.toDtos(rep.getAllByMovieId(movieId));
    }

    @Override
    public List<RoomMovieDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public List<RoomMovieDto> findRoomMovieByMovieId(Long movieId, LocalDate startDay) {
        return mapper.toDtos(rep.findRoomMovieByMovieId(movieId, startDay));
    }

    @Override
    public List<RoomMovieDto> findRoomMovieByCinemaId(Long cinemaId, LocalDate startDate) {
        return mapper.toDtos(rep.findRoomMovieByCinemaId(cinemaId,startDate));
    }
}
