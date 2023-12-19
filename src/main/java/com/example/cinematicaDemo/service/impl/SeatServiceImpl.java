package com.example.cinematicaDemo.service.impl;

import com.example.cinematicaDemo.exceptions.SeatNotFoundException;
import com.example.cinematicaDemo.service.RoomService;
import com.example.cinematicaDemo.service.SeatService;
import com.example.cinematicaDemo.dao.SeatRep;
import com.example.cinematicaDemo.mappers.SeatMapper;
import com.example.cinematicaDemo.models.dto.RoomDto;
import com.example.cinematicaDemo.models.dto.SeatDto;
import com.example.cinematicaDemo.models.request.SaveSeatRequest;
import com.example.cinematicaDemo.models.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    SeatMapper mapper = SeatMapper.INSTANCE;

    private final SeatRep rep;
    private final RoomService roomService;


    @Autowired
    public SeatServiceImpl(SeatRep rep, RoomService roomService) {
        this.rep = rep;
        this.roomService = roomService;
    }

    @Override
    public SeatDto save(SeatDto seatDto) {
        return mapper.toDto(rep.save(mapper.toEntity(seatDto)));
    }

    @Override
    public SeatDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new SeatNotFoundException("Seat not found!")));
    }

    @Override
    public SeatDto delete(Long id) {
        SeatDto seatDto = findById(id);
        seatDto.setActive(false);
        return save(seatDto);
    }

    @Override
    public List<SeatDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public List<SeatDto> findSeatsByRoomId(Long roomMovieId) {
        return mapper.toDtos(rep.findSeatsByRoomId(roomMovieId));
    }

    @Override
    public Response create(SaveSeatRequest seat) {
        RoomDto roomDto = roomService.findById(seat.getRoomId());

        SeatDto seatDto = new SeatDto();
        int seatCount = roomDto.getSeatCount();
        int rows = seat.getRowsCount();
        int seats =seatCount/rows;
        int i;
        int j;

        for (i = 1; i <=rows; i++){
            for (j=1; j <= seats; j++){
                seatDto.setRow(i);
                seatDto.setNumber(j);
                seatDto.setRoom(roomDto);
                save(seatDto);
            }
        }
        return new Response("Saved successfully!");
    }
}
