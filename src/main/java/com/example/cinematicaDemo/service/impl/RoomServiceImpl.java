package com.example.cinematicaDemo.service.impl;

import com.example.cinematicaDemo.exceptions.RoomNotFoundException;
import com.example.cinematicaDemo.service.CinemaService;
import com.example.cinematicaDemo.service.RoomService;
import com.example.cinematicaDemo.dao.RoomRep;
import com.example.cinematicaDemo.mappers.RoomMapper;
import com.example.cinematicaDemo.models.dto.CinemaDto;
import com.example.cinematicaDemo.models.dto.RoomDto;
import com.example.cinematicaDemo.models.request.SaveRoomRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    RoomMapper mapper = RoomMapper.INSTANCE;
    private final RoomRep rep;
    private final CinemaService cinemaService;


    @Autowired
    public RoomServiceImpl(RoomRep rep, CinemaService cinemaService) {
        this.rep = rep;
        this.cinemaService = cinemaService;
    }

    @Override
    public RoomDto save(RoomDto roomDto) {
        return mapper.toDto(rep.save(mapper.toEntity(roomDto)));
    }

    @Override
    public RoomDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found!")));
    }

    @Override
    public RoomDto delete(Long id) {
        RoomDto roomDto = findById(id);
        roomDto.setActive(false);
        return save(roomDto);
    }

    @Override
    public List<RoomDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public RoomDto create(SaveRoomRequest room) {
        CinemaDto cinema = cinemaService.findById(room.getCinemaId());
        RoomDto roomDto = new RoomDto();
        roomDto.setName(room.getName());
        roomDto.setSeatCount(room.getSeatCount());
        roomDto.setCinema(cinema);
        return save(roomDto);
    }

    @Override
    public RoomDto findRoomByRoomMovieId(Long roomMovieId) {
        return mapper.toDto(rep.findRoomByRoomMovieId(roomMovieId));
    }
}
